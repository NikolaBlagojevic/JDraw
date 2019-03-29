/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import static org.junit.Assert.fail;

import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.event.MouseInputAdapter;

import jdraw.framework.*;
import jdraw.figures.*;
	
/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Nikola Blagojevic
 *
 */
public class StdDrawModel implements DrawModel{
	
	LinkedList<Figure> figurelist= new LinkedList<Figure>();
	public LinkedList<DrawView> drawviews = new LinkedList<DrawView>();	
	public LinkedList<ConcreteListener> figurelisteners = new LinkedList<ConcreteListener>();
	LinkedList<ConcreteModelListener> drawmodellistener = new LinkedList<ConcreteModelListener>();
		
	//DrawModelListener implementation
	public class ConcreteModelListener implements DrawModelListener {
		public StdDrawModel model;
		@Override
		public void modelChanged(DrawModelEvent e) {
			model.refresh();
						
		}		
	}
	

	@Override
	public void addFigure(Figure f) {
		// TODO to be implemented
		if (!figurelist.contains(f)) {
		figurelist.add(f);
		ConcreteListener l = new ConcreteListener();
		l.model = this;
		figurelisteners.add(l);		
		
		DrawModelEvent e = new DrawModelEvent(this,null,DrawModelEvent.Type.FIGURE_ADDED);
		
		for (ConcreteModelListener i : drawmodellistener) {
			i.model = this;
			i.modelChanged(e);
		}
		} else {
			System.out.println("Figure already in the model");
		}
	}

	@Override
	public Iterable<Figure> getFigures() {
		return figurelist ; 
		}

	@Override
	public void removeFigure(Figure f) {
		
		if (figurelist.contains(f)) {
		int index = figurelist.indexOf(f);
		f.removeFigureListener(figurelisteners.get(index));
		figurelist.remove(f);
		figurelisteners.remove(figurelisteners.get(index));
		
		DrawModelEvent e = new DrawModelEvent(this,null,DrawModelEvent.Type.FIGURE_REMOVED);
		for (ConcreteModelListener i : drawmodellistener)
			i.modelChanged(e);
		}
				
	}
	public void addModelChangeListener(ConcreteModelListener listener) {	
		drawmodellistener.add(listener);		
	}

	public void removeModelChangeListener(ConcreteModelListener listener) {	  
		drawmodellistener.remove(listener);		
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
		if (figurelist.contains(f) && (index < (figurelist.size()-1))) {
			figurelist.add(index, f);
			DrawModelEvent e = new DrawModelEvent(this,null,DrawModelEvent.Type.DRAWING_CHANGED);
			for (ConcreteModelListener i : drawmodellistener)
				i.modelChanged(e);
		} else {
			System.out.println("IllegalArgumentException expected");
			
		}
		//System.out.println("StdDrawModel.setFigureIndex has to be implemented");
	
	}

	@Override
	public void removeAllFigures() {
		for (Figure i : figurelist) {
			int index = figurelist.indexOf(i);
			i.removeFigureListener(figurelisteners.get(index));
		}
		figurelist.clear();     // TODO to be implemented  
		figurelisteners.clear();
		DrawModelEvent e = new DrawModelEvent(this,null,DrawModelEvent.Type.DRAWING_CLEARED);
		for (ConcreteModelListener i : drawmodellistener)
			i.modelChanged(e);		

	}
	public void refresh() {
		for(int i = 0 ; i<drawviews .size(); i++ ) 
			drawviews.get(i).repaint();					
		
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		// TODO Auto-generated method stub
		
	}
		}



