/**
 * 
 */


		var panel = document.getElementById("divpanel");
		var velocidad = 10;
		var pos = 0;
		
		function iralaizquierda(){
			var vi = velocidad*(-1);
			pos = pos + vi;
			panel.style.left = pos + "%";			
		}
		
		function iraladerecha(){
			var vd = velocidad;
			pos = pos + vd;
			panel.style.left = pos + "%";
		}


