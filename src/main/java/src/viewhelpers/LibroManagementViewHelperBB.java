package src.viewhelpers;

import src.entity.Libro;

public class LibroManagementViewHelperBB extends
		AbstractEntityManagementViewHelper<Libro> {


	
	/*******************************************/
	// GETTERS AND SETTERS

	@Override
	public Integer getId() {
		return getTransferObject().getId();
	}
	
	public void setId(Integer id) {
		getTransferObject().setId(id);
	}
	
	public String getTitle() {
		return getTransferObject().getTitle();
	}
	public void setTitle(String title) {
		getTransferObject().setTitle(title);
	}
	public String getAuthor() {
		return getTransferObject().getAuthor();
	}
	public void setAuthor(String author) {
		getTransferObject().setAuthor(author);
	}



}
