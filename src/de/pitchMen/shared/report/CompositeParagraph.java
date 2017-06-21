package de.pitchMen.shared.report;

import java.util.ArrayList;

/**
	 * Subklasse von Paragraph.
	 * Einzelne Absätze werden als Menga dargestellt, welche als Unterabschnitte in einer ArrayList abgelegt und verwaltet werden.
	 * 
	 * @author
	 */
	public class CompositeParagraph extends Paragraph {

	    /**
	     * 
	     */
	    private static final long serialVersionUID = 1L;

	    /**
	     * 
	     */
	    private ArrayList<SimpleParagraph> subParagraphs = new ArrayList<SimpleParagraph>();

	    /**
	     * @return
	     */
	    public ArrayList<SimpleParagraph> getSubParagraphs() {
	        return subParagraphs;
	    }
	    
	    public void addSubParagraph(SimpleParagraph p) {
	    	subParagraphs.add(p);
	    }
	    
	    public void removeSubParagraph(SimpleParagraph p) {
	        subParagraphs.remove(p);
	      }
	    
	    public int getNumParagraphs() {
	    	return subParagraphs.size();
	    };
	    

	    
	    /**
	     * @param value
	     */
	    public void setSubParagraphs(ArrayList<SimpleParagraph> value) {
	       
	    }
	    public SimpleParagraph getParagraphAt(int i) {
	        return subParagraphs.get(i);
	      }
	    
	    @Override
	    public String toString() {

	      StringBuffer result = new StringBuffer();


	      for (int i = 0; i < subParagraphs.size(); i++) {
	        SimpleParagraph p = subParagraphs.get(i);

	        result.append(p.toString() + "<br>");
	      }

	      return result.toString();
	    }
	}

