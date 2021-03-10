/**
 * 
 */


	var velocidad = 10;
	var pos = 0;
	
	function iralaizquierda(panelname, velocidad){
		var panel = document.getElementById(panelname);						
		var vi = velocidad*(-1);
		pos = pos + vi;
		panel.style.left = pos + "%";	
		alert("panelname = " + panelname + ", velocidad = " + velocidad);						
	}
	
	function iraladerecha(panelname, velocidad){
		var panel = document.getElementById(panelname);						
		var vd = velocidad;
		pos = pos + vd;
		panel.style.left = pos + "%";
				alert("panelname = " + panelname + ", velocidad = " + velocidad);						
		
	}



