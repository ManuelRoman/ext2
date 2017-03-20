var x;
x=$(document);
x.ready(inicializarEventos);
function inicializarEventos() {
    var x;
    x=$("#buscaDNI");
    x.on("keyup",function(event){
    	sugerenciaDNI(event);
	})
}

function sugerenciaDNI(event){
	var dato = $("#buscaDNI").val();
	console.log("Antes del if: "+dato);
	if((event.keyCode>=48 && event.keyCode<=57) || (event.keyCode>=65 && event.keyCode<=90) || (event.keyCode >= 96 && event.keyCode <= 105)){
		console.log("Después del if: "+dato);
		$.ajax({ //Con esto se trabaja con ajax a través de jquery
	        async:true,
	        type: "POST",
	        dataType: "html", //tipo de dato que se va ha recibir
	        contentType: "application/x-www-form-urlencoded", //como se envia los datos
	        url:"sugerir", //url a la que se envia
	        data:"dato="+dato, //cadena, los datos de la petición
	        beforeSend: inicioEnvio,
	        success: llegadaDatos, //función que recupera los datos devueltos
	        timeout: 4000, //tiempo máximo a esperar para la respuesta
	        error: problemas //Si hay algún problema, se ejecuta
	    });
	}
}

function inicioEnvio() {
    //no se hace nada
}

function llegadaDatos(datos) {
	console.log(datos);
	var dnis = jQuery.parseJSON(datos);
	jQuery.each( dnis, function( i, val ) {
		console.log(i+"->"+val);
		});
	jQuery.each( dnis, function( i, val ) {
		$("#dnis").append("<option value=\""+val+"\">");
		});
}

function problemas() {
	console.log("Hay un problema en el servidor.");
}