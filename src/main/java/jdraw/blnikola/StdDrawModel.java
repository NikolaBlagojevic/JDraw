/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.blnikola;

import java.util.LinkedList;
import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Nikola Blagojevic
 *
 */
public class StdDrawModel implements DrawModel {

	@Override
	public void addFigure(Figure f) {
		this.addFigure(f); // to be implemented
		// System.out.println("StdDrawModel.addFigure has to be implemented");
	}

	@Override
	public Iterable<Figure> getFigures() {
		return (Iterable<Figure>) getFigures().iterator(); //  to be implemented  
		
		//System.out.println("StdDrawModel.getFigures has to be implemented");
		//return new LinkedList<Figure>(); // Only guarantees, that the application starts -- has to be replaced !!!
	}

	@Override
	public void removeFigure(Figure f) {
		this.removeFigure(f); //  to be implemented  
		//System.out.println("StdDrawModel.removeFigure has to be implemented");
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		this.addModelChangeListener(listener);// to be implemented  
		//System.out.println("StdDrawModel.addModelChangeListener has to be implemented");
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		this.removeModelChangeListener(listener); // to be implemented  
		//System.out.println("StdDrawModel.removeModelChangeListener has to be implemented");
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	@Override
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		// TODO to be implemented  
		System.out.println("StdDrawModel.setFigureIndex has to be implemented");
	}

	@Override
	public void removeAllFigures() {
		// TODO to be implemented  
		System.out.println("StdDrawModel.removeAllFigures has to be implemented");
	}

}
