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
	    public String toString() {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @return
	     */
	    public ArrayList<SimpleParagraph> getSubParagraphs() {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @param value
	     */
	    public void setSubParagraphs(ArrayList<SimpleParagraph> value) {
	        // TODO implement here
	    }

	}

