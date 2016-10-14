package com.eficacia.taglibs;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTaglib extends SimpleTagSupport{

	private String uri;
	 private int offset;
	 private int conteo;
	 private int limite = 5;
	 private int steps = 5;
	 private String anterior = "Anterior";
	 private String siguiente = "Siguiente";
	 
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
	    
	   for(int itr=0;itr<conteo;itr+=steps) {
	    if(offset==itr)
	     out.write(constructLink((itr/5+1)-1 *steps, String.valueOf(itr/5+1), "active", true));
	    else
	     out.write(constructLink(itr/5*steps, String.valueOf(itr/5+1), null , false));
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
	
}