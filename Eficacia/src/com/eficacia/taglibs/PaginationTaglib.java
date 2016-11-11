package com.eficacia.taglibs;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTaglib extends SimpleTagSupport{

	private String uri;
	 private int offset=0;
	 private int conteo;
	 private int limite;
	 private int steps = 15;
	 private String anterior = "Anterior";
	 private String siguiente = "Siguiente";
	 private String soeid;
	 private String razonSocial;
	 private int flag ;
	 
	 private Writer getWriter() {
	  JspWriter out = getJspContext().getOut();
	  return out;
	 }
	 
	 @Override
	 public void doTag() throws JspException {
	  Writer out = getWriter();
	  try {
	   out.write("<nav>");
	   out.write("<ul class=\"pagination\">");
	    
	   if(offset<steps)
	    out.write(constructLink(1, anterior, "disabled", true));
	   else
	    out.write(constructLink(offset-steps, anterior, null, false));
	   
	   
	   int limite=0;
	   if(offset + 30 > conteo){
		   limite = offset + 15;
		   System.out.println("Mas 15 offset " + offset + " limite "+ limite);
	   }else{
		   limite = offset + 30;
		   System.out.println("Mas 30 offset " + offset + " limite "+ limite);
	   }
	   
	   
	   for(int itr=offset;itr<limite;itr+=steps) {
	    if(offset==itr){
	    	out.write(constructLink((itr/15+1)-1 *steps, String.valueOf(itr/15+1), "active", true));
	    }else{
	    	out.write(constructLink(itr/15*steps, String.valueOf(itr/15+1), null , false));
	    }
	   
	   }
	 
	   if(offset+steps>=conteo)
	    out.write(constructLink(offset+steps, siguiente, "disabled", true));
	   else
	    out.write(constructLink(offset+steps, siguiente, null , false));
	    
	    
	   out.write("</ul>");
	   out.write("</nav>");
	  } catch (java.io.IOException ex) {
	   throw new JspException("Error in Paginator tag", ex);
	  }
	 }
	 
	 
	 private String constructLink(int page, String text, String className, boolean disabled) {
		 StringBuilder link = new StringBuilder("<li");
		  if (className != null) {
		   link.append(" class=\"");
		   link.append(className);
		   link.append("\"");
		  }
		  /*if(disabled && page == 1){
			 link.append(">").append("<a href=\"#\">"+text+"</a></li>");  
		  }else if(disabled){
		   link.append(">").append("<a href=\"#\">"+text+"</a></li>");
		  }else if(page > 20){
			  link.append(">").append("<a href=\""+uri+"?offset="+page + "&soeid=" + soeid + "&razonSocial=" + razonSocial +  "\"><div onclick=\"aumentarNumeroPaginacion();\" class=\"numeroPaginacion\">"+text+"</div></a></li>");
		  }else{
			  link.append(">").append("<a href=\""+uri+"?offset="+page + "&soeid=" + soeid + "&razonSocial=" + razonSocial +  "\"><div id=\"numeroPaginacion"+page+"\" class=\"numeroPaginacion\">"+text+"</div></a></li>");
		  }*/
		  
		  if(disabled)
			   link.append(">").append("<a href=\"#\">"+text+"</a></li>");
			  else
			   link.append(">").append("<a href=\""+uri+"?offset="+page + "\">"+text+"</a></li>");
		   
		  return link.toString();
		 }
	 
	 public String getUri() {
		  return uri;
		 }
		 
		 public void setUri(String uri) {
		  this.uri = uri;
		 }
		 
		 public int getOffset() {
		  return offset;
		 }
		 
		 public void setOffset(int offset) {
		  this.offset = offset;
		 }
		 
		 public int getConteo() {
		  return conteo;
		 }
		 
		 public void setConteo(int conteo) {
		  this.conteo = conteo;
		 }
		 
		 public int getLimite() {
		  return limite;
		 }
		 
		 public void setLimite(int limite) {
		  this.limite = limite;
		 }
		 
		 public String getAnterior() {
		  return anterior;
		 }
		 
		 public void setAnterior(String anterior) {
		  this.anterior = anterior;
		 }
		 
		 public String getSiguiente() {
		  return siguiente;
		 }
		 
		 public void setSiguiente(String siguiente) {
		  this.siguiente = siguiente;
		 }
		 
		 public int getSteps() {
		  return steps;
		 }
		 
		 public void setSteps(int steps) {
		  this.steps = steps;
		 }

		public String getSoeid() {
			return soeid;
		}

		public void setSoeid(String soeid) {
			this.soeid = soeid;
		}

		public String getRazonSocial() {
			return razonSocial;
		}

		public void setRazonSocial(String razonSocial) {
			this.razonSocial = razonSocial;
		}
	
}
