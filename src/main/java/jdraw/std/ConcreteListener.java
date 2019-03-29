package jdraw.std;


import jdraw.framework.*;
//import jdraw.figures.*;
import jdraw.std.StdDrawModel;



//FigureListener implementation
		public class ConcreteListener implements FigureListener {
		//private Figure f;
		public StdDrawModel model;		
		//ConcreteListener (Figure f) {this.f = f; f.addFigureListener(this); }		
		public final void figureChanged(FigureEvent e) {
			model.refresh();				
			}		
		}
		