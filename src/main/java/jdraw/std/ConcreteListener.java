package jdraw.std;


import jdraw.framework.*;
//import jdraw.figures.*;
import jdraw.std.StdDrawModel;


// MR this class should not be necessary
// any class that will implement the FigureListener interface will do so
// directly, instead of extending this class
// Note: In Java one can only extend from a single class but may implement
// several interfaces. This is a very important difference to be aware of.

//FigureListener implementation
		public class ConcreteListener implements FigureListener {
		//private Figure f;
		public StdDrawModel model;		
		//ConcreteListener (Figure f) {this.f = f; f.addFigureListener(this); }		
		public final void figureChanged(FigureEvent e) {
			model.refresh();				
			}		
		}
		
