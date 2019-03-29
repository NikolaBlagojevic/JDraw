/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.*;
import jdraw.std.ConcreteListener;
import jdraw.std.StdDrawModel;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */


@SuppressWarnings("serial")
public final class Oval implements Figure{
	/**
	 * Use the java.awt.Ellipse2D in order to save/reuse code.
	 */
	private final Ellipse2D.Double oval;
	public LinkedList<FigureListener> listeners = new LinkedList<FigureListener>();
	
	public StdDrawModel model;
	
	
	
	/**
	 * Create a new rectangle of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle's width
	 * @param h the rectangle's height
	 */
	public Oval(int x, int y, int w, int h) {
		oval = new Ellipse2D.Double( x,y,w,h);
		//Rectangle2D rect = new Rectangle2D.Double(x, y, w, h);
		//oval.setFrame(rect);
		
		
	}
	

	/**
	 * Draw the rectangle to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	@Override
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(Color.WHITE);
		g2D.fill(oval);
		g2D.setColor(Color.BLACK);
		g2D.draw(oval);
		
	}
	
	@Override
	public void setBounds(Point origin, Point corner) {
		oval.setFrameFromDiagonal(origin, corner);
		//DrawModelEvent e = new DrawModelEvent(this.model, this, DrawModelEvent.Type.FIGURE_CHANGED);
		FigureEvent fe = new FigureEvent(this); // TODO notification of change
		for (FigureListener i : listeners) {
			i.figureChanged(fe);
		}
	
	}

	@Override
	public void move(int dx, int dy) {
		System.out.println("Move doesn't work");
		//oval.setLocation(oval.x + dx, oval.y + dy);		
		//DrawModelEvent e = new DrawModelEvent(this.model, this, DrawModelEvent.Type.FIGURE_CHANGED);
		//if (dx!=0 && dy!=0) {
			//FigureEvent fe = new FigureEvent(this); // TODO notification of change
			//for (FigureListener i : listeners) {
			//i.figureChanged(fe);
			//}
			
		//}
	}

	@Override
	public boolean contains(int x, int y) {
		return oval.contains(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return oval.getBounds();
	}

	/**
	 * Returns a list of 8 handles for this Rectangle.
	 * @return all handles that are attached to the targeted figure.
	 * @see jdraw.framework.Figure#getHandles()
	 */	
	@Override
	public List<FigureHandle> getHandles() {
		return null;
	}


	@Override
	public Figure clone() {
		return null;
	}
	@Override
	public void addFigureListener(FigureListener listener) {
		listeners.add(listener);
		
		
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		listeners.remove(listener);		
	}
	
	public void getModel(StdDrawModel sourcemodel) {
		StdDrawModel model =  sourcemodel;
	}


		



		
	
}

