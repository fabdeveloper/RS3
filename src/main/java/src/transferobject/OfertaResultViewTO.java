/**
 * 
 */
package src.transferobject;

import src.entity.Oferta;

/**
 * @author fabo_
 *
 */
public class OfertaResultViewTO {
	
	
	private String outputText1;
	private String outputText2;
	private String outputText3;
	private String outputText4;
	private String outputText5;
	private String outputText6;
	private String outputText7;
	
	private String buttontext;

		
	
	
	public static OfertaResultViewTO getOfertaResultViewTO(Oferta oferta) {
		OfertaResultViewTO resultview = new OfertaResultViewTO();
		resultview.setButtontext("no informado");
		resultview.setOutputText1(oferta.getDescripcion());
		resultview.setOutputText2(oferta.getName());
		resultview.setOutputText3(oferta.getArticulo().getName());
		resultview.setOutputText4(oferta.getArticulo().getDescripcion());
		resultview.setOutputText5(oferta.getPrecio().toString());
		resultview.setOutputText6(oferta.getStock().toString());
		resultview.setOutputText7(oferta.getId().toString());
		
		return resultview;
	}
	
	
	
	
	
	public String getOutputText1() {
		return outputText1;
	}

	public void setOutputText1(String outputText1) {
		this.outputText1 = outputText1;
	}

	public String getOutputText2() {
		return outputText2;
	}

	public void setOutputText2(String outputText2) {
		this.outputText2 = outputText2;
	}

	public String getOutputText3() {
		return outputText3;
	}

	public void setOutputText3(String outputText3) {
		this.outputText3 = outputText3;
	}

	public String getOutputText4() {
		return outputText4;
	}

	public void setOutputText4(String outputText4) {
		this.outputText4 = outputText4;
	}

	public String getOutputText5() {
		return outputText5;
	}

	public void setOutputText5(String outputText5) {
		this.outputText5 = outputText5;
	}

	public String getOutputText6() {
		return outputText6;
	}

	public void setOutputText6(String outputText6) {
		this.outputText6 = outputText6;
	}

	public String getOutputText7() {
		return outputText7;
	}

	public void setOutputText7(String outputText7) {
		this.outputText7 = outputText7;
	}

	public String getButtontext() {
		return buttontext;
	}

	public void setButtontext(String buttontext) {
		this.buttontext = buttontext;
	}

	
	
	
	

	
	
	
	
	
	
	

}
