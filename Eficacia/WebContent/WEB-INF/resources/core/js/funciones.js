  $(function() {
	  
	  $('#RazonSocial').keydown(function(e) {
		  
		  if ( e.ctrlKey || e.altKey) {
				  e.preventDefault();
      		  } else {
      			  var key = e.keyCode;
      			  if (!((key == 9) || (key == 13) || (key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) 
      					 || (key >= 65 && key <= 90) || (key ==110) || (key == 190))) {
      				  e.preventDefault();
      			  }
      		  }
	  });
	  
	  
	  $('#Digitos').keydown(function(e) {
		  
		  if ( e.ctrlKey || e.altKey || e.shiftKey) {
				  e.preventDefault();
      		  } else {
      			  var key = e.keyCode;
      			  if (!((key == 9) || (key >= 48 && key <= 57) || (key >= 96 && key <= 105) || (key == 8))) {
      				  e.preventDefault();
      			  }
      		  }
	  });
	  
	  
	  $('#Telefono').keydown(function(e) {
		  
		  if ( e.ctrlKey || e.altKey || e.shiftKey) {
				  e.preventDefault();
      		  } else {
      			  var key = e.keyCode;
      			  if (!((key == 9) || (key >= 48 && key <= 57) || (key >= 96 && key <= 105) || (key == 8))) {
      				  e.preventDefault();
      			  }
      		  }
	  });


	
	  $('#Soeid').keydown(function(e) {
		  
		  if ( e.ctrlKey || e.altKey || e.shiftKey) {
				  e.preventDefault();
      		  } else {
      			  var key = e.keyCode;
      			  if (!((key == 9) || (key >= 48 && key <= 90) || (key >= 96 && key <= 105) || (key == 8)) ) {
      				  e.preventDefault();
      			  }
      		  }
	  });
	  
	  
	  $('#Nombre').keydown(function(e) {
		  
		  if ( e.ctrlKey || e.altKey ) {
				  e.preventDefault();
      		  } else {
      			  var key = e.keyCode;
      			if (!((key == 9) || (key == 13) || (key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) 
     					 || (key >= 65 && key <= 90))){
      				  e.preventDefault();
      			  }
      		  }
	  });
	  
	  
	  $('#Ejecutivo').keydown(function(e) {
		  
		  if ( e.ctrlKey || e.altKey ) {
				  e.preventDefault();
      		  } else {
      			  var key = e.keyCode;
      			if (!((key == 9) || (key == 13) || (key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) 
     					 || (key >= 65 && key <= 90))){
      				  e.preventDefault();
      			  }
      		  }
	  });

  });