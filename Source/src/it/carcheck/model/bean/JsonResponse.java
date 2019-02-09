package it.carcheck.model.bean;

public class JsonResponse {

	public JsonResponse() {}
	
	public JsonResponse(int status, String message) {
		this.JsonResponseStatus = status;
		this.JsonResponseMessage = message;
	}
	
	public JsonResponse(int status, String message, Object content) {
		this.JsonResponseStatus = status;
		this.JsonResponseMessage = message;
		this.JsonResponseContent = content;
	}
	
	public int getJsonResponseStatus() {
		return JsonResponseStatus;
	}

	public void setJsonResponseStatus(int jsonResponseStatus) {
		JsonResponseStatus = jsonResponseStatus;
	}

	public String getJsonResponseMessage() {
		return JsonResponseMessage;
	}

	public void setJsonResponseMessage(String jsonResponseMessage) {
		JsonResponseMessage = jsonResponseMessage;
	}

	public Object getJsonResponseContent() {
		return JsonResponseContent;
	}

	public void setJsonResponseContent(Object jsonResponseContent) {
		JsonResponseContent = jsonResponseContent;
	}

	private int JsonResponseStatus;
	private String JsonResponseMessage;
	private Object JsonResponseContent;
}
