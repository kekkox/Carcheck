package it.carcheck.control.request;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleInspectionManager;
import it.carcheck.model.VehicleManager;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.utility.JsonResponse;

import java.io.File;

@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class WorkshopInspectionOperation extends HttpServlet implements IAction{
	
	public String execute(HttpServletRequest request, HttpServletResponse response)  throws ActionException, ServletException, IOException, ParseException{
		
		String operation = request.getParameter("button");
		String licenseplate = request.getParameter("licensePlate");
		
		Gson gson = new Gson();
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		 PrintWriter writer = response.getWriter();
		 WorkshopBean workshop = (WorkshopBean) request.getSession().getAttribute("user");
			VehicleInspectionManager inspectionman = (VehicleInspectionManager) VehicleInspectionManager.getInstance();
		 
		/*   response.setContentType("text/html;charset=UTF-8");

        OutputStream out = null;
        InputStream filecontent = null;
    	Part filePart = request.getPart("photofile");
		 String fileName = filePart.getName();
		 PrintWriter writer = response.getWriter();
		 writer.println(fileName);*/
		
		try {
			
			
			/* out = new FileOutputStream(new File(UPLOAD_DIRECTORY + File.separator+ fileName));
	            filecontent = filePart.getInputStream();

	            int read = 0;
	            final byte[] bytes = new byte[1024];

	            while ((read = filecontent.read(bytes)) != -1) {
	                out.write(bytes, 0, read);
	            }
	            writer.println("New file " + fileName + " created at " + UPLOAD_DIRECTORY);*/
	
			 
			/*writer.println(filePart);
		    InputStream fileInputStream = filePart.getInputStream();
		
		    File fileToSave = new File(UPLOAD_DIRECTORY+ filePart.getSubmittedFileName());
		    Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);*/
			
			//writer.println(licenseplate);
			VehicleBean vehicle = VehicleManager.getInstance().doRetriveVehicle(licenseplate);
			if (vehicle == null) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, ERROR_LICENSEPLATE)));
				return "inspectionView";
			}
			
			String inspectionDate = request.getParameter("inspectionDate");
			int km = Integer.parseInt(request.getParameter("km"));
			String state = request.getParameter("state");
			String photo = request.getParameter("photo");
			if (operation.equals("ADD")) 
			{
				//PrintWriter writer = response.getWriter();
	
				VehicleInspectionBean inspection = new VehicleInspectionBean();
				inspection.setVehicle(vehicle.getLicensePlate());
				inspection.setInspectionDate(Date.valueOf((inspectionDate)));
				inspection.setKm(km);
				int year = Integer.parseInt(inspectionDate.substring(0,4));
				year=year+2;
				String expirationdate = inspectionDate.substring(4);
				expirationdate=year+expirationdate;
				
					inspection.setExpirationDate(Date.valueOf(expirationdate));
				inspection.setPhoto("photo");
				if(state=="1")
				inspection.setResult(true);
				else 
					inspection.setResult(false);
				inspection.setWorkShop(workshop.getId());
			inspectionman.doInsert(inspection);
			
				}
				
			else if(operation.equals("EDIT"))
			{
				int inspectionKey = Integer.parseInt(request.getParameter("inspectionKey"));
				VehicleInspectionBean inspection = inspectionman.doRetrieveByKey(workshop, inspectionKey);
				inspection.setVehicle(vehicle.getLicensePlate());
				inspection.setInspectionDate(Date.valueOf((inspectionDate)));
				inspection.setKm(km);
				int year = Integer.parseInt(inspectionDate.substring(0,4));
				year=year+2;
				String expirationdate = inspectionDate.substring(4);
				expirationdate=year+expirationdate;
				inspection.setExpirationDate(Date.valueOf(expirationdate));
				inspection.setPhoto(photo);
				if(state=="1")
				inspection.setResult(true);
				else 
				inspection.setResult(false);
				inspection.setWorkShop(workshop.getId());
				inspectionman.doSave(inspection);
				

			}
		} 
		
		catch (SQLException e) {
			throw new ActionException();
		}
		return null;

	}
	/*private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }*/

	private static final String INSERT_SUCCESS = "Hai inserito una nuova revisione";
	private static final String EDIT_SUCCESS = "Le modifiche sono state effettuate correttamente";
	private static final String ERROR_LICENSEPLATE = "La targa non � presente nei nostri sistemi";
	private final String UPLOAD_DIRECTORY = "../workshop/imgVheicle";

}
