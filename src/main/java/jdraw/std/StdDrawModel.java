/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

// MR in order to be able to build with gradle I had to remove the line below
// import static org.junit.Assert.fail;
// why is this in here, anyway?

import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.event.MouseInputAdapter;

// MR in Java it is better to avoid wildcard imports
// instead, you should list all classes to be imported explicitly
import jdraw.framework.*;
import jdraw.figures.*;
	
/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Nikola Blagojevic
 *
 */
// MR regarding your question from the mail: the StdDrawModel should implement
// the FigureListener interface directly
// to do so, add it below and implement all necessary functions
public class StdDrawModel implements DrawModel{
	
	LinkedList<Figure> figurelist= new LinkedList<Figure>();
        // MR this list of drawviews should not be necessary and actually
        // defeats the purpose of the observer pattern!
	public LinkedList<DrawView> drawviews = new LinkedList<DrawView>();	
        // MR this list below should then contain DrawModelListener instances
        // also note the tip on the exercise session slides on how to avoid
        // the ConcurrentModificationException thrown by one of the JUnit tests
        // for Rect.java
	public LinkedList<ConcreteListener> figurelisteners = new LinkedList<ConcreteListener>();
	LinkedList<ConcreteModelListener> drawmodellistener = new LinkedList<ConcreteModelListener>();
		
        // MR this class becomes obsolete after fixing the feedback
	// DrawModelListener implementation
        // Note: when implementing the FigureListener interface, you will have
        // to program the figureChanged(FigureEvent e) function
        // This should then take care of notifying all registered listeners.
        // Be sure to take a look at this resource and exercise 3 where we
        // practiced working with ActionListeners:
        // https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html#section2
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
                // MR maybe also assert that f is not null
		if (!figurelist.contains(f)) {
		figurelist.add(f);
		ConcreteListener l = new ConcreteListener();
                // MR if you do things like this, better use a Setter
                // (keyword: data hiding)
		l.model = this;
		figurelisteners.add(l);		
		
                // MR this piece of code below will turn up a lot more often
                // below -> better write a new function and call that
                // e.g. notifyAllListeners(DrawModelEvent e)
		DrawModelEvent e = new DrawModelEvent(this,null,DrawModelEvent.Type.FIGURE_ADDED);
		
		for (ConcreteModelListener i : drawmodellistener) {
                        // MR why do you overwrite the model again?
			i.model = this;
			i.modelChanged(e);
		}
		} else {
			System.out.println("Figure already in the model");
		}
	}

	@Override
	public Iterable<Figure> getFigures() {
                // MR note the tip from the exercise session slides regarding
                // data hiding
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

        // MR these two functions should be implemented into those templates at
        // the end of this file!
	public void addModelChangeListener(ConcreteModelListener listener) {	
                // MR same assertions as done in addFigure could be performed
		drawmodellistener.add(listener);		
	}

	public void removeModelChangeListener(ConcreteModelListener listener) {	  
                // MR same assertions as done in removeFigure could be performed
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
                // MR also assert that the index is not negative
                // the last assertion should either be <= or without the -1
		if (figurelist.contains(f) && (index < (figurelist.size()-1))) {
                        // MR without removing the figure first, you will end
                        // up with duplicate figures
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
                // MR what is this todo? The clear() function is provided by the
                // Java List class. See also here:
                // https://docs.oracle.com/javase/7/docs/api/java/util/List.html#clear()
		figurelist.clear();     // TODO to be implemented  
		figurelisteners.clear();
		DrawModelEvent e = new DrawModelEvent(this,null,DrawModelEvent.Type.DRAWING_CLEARED);
		for (ConcreteModelListener i : drawmodellistener)
			i.modelChanged(e);		

	}

        // MR This function should become obsolete once the feedback is fixed.
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



