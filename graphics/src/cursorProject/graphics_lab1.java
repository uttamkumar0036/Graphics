package cursorProject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class graphics_lab1 {
	public static void main(String args[]) throws Exception {
		
		
	    JFrame f = new JFrame("Drawing Line");
	    f.setSize(1000,1000);
	    f.setLocation(300, 100);
	    f.setResizable(true);
	    
	    JPanel btPanel=new JPanel(); 
	    btPanel.setBackground(Color.ORANGE);
	    JButton directLine=new JButton("Direct Line");
	    directLine.setBounds(0,0, 25,125);
	    directLine.setBackground(Color.LIGHT_GRAY);
	    btPanel.add(directLine);
	    
	    JButton dda=new JButton("DDA");
	    directLine.setBounds(0, 0, 100, 25);
	    dda.setBackground(Color.LIGHT_GRAY);
	    btPanel.add(dda);
	    
	    JButton bresenham=new JButton("Bresenham");
	    directLine.setBounds(0, 0, 100, 25);
	    bresenham.setBackground(Color.LIGHT_GRAY);
	    btPanel.add(bresenham);
	    
	    JButton bresenhamcircle=new JButton("Bresenham Circle");
	    directLine.setBounds(0, 0, 100, 25);
	    bresenhamcircle.setBackground(Color.LIGHT_GRAY);
	    btPanel.add(bresenhamcircle);
	    JButton midpointcircle=new JButton("Midpoint Circle");
	    directLine.setBounds(0, 0, 100, 25);
	    midpointcircle.setBackground(Color.LIGHT_GRAY);
	    btPanel.add(midpointcircle);
	    
	    JPanel txtPanel=new JPanel();
	    txtPanel.setBounds(0,0, 100, 25);
	    
	    JTextField txtfd=new JTextField();
	    txtfd.setEditable(false);
	    txtPanel.add(txtfd);
	    txtfd.setText("");
	    
	    directLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtfd.setText("Direct Line");
			}
		});
	    
	    dda.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				txtfd.setText("DDA");
			}
		});
	    
	    bresenham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtfd.setText("Bresenham");
			}
		});
	    
	    bresenhamcircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtfd.setText("Bresenham Circle");
			}
		});
	    
	    midpointcircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtfd.setText("Midpoint Circle");
			}
		});
	    
	    JPanel p = new JPanel() {
	        Point pointStart = null;
	        Point pointEnd   = null;
	        {
	            addMouseListener(new MouseAdapter() {
	                public void mousePressed(MouseEvent e) {
	                    pointStart = e.getPoint();
	                }
	                public void mouseReleased(MouseEvent e) {
	                    pointStart = null;
	                }
	            });
	            addMouseMotionListener(new MouseMotionAdapter() {
	                public void mouseMoved(MouseEvent e) {
	                    pointEnd = e.getPoint();
	                }

	                public void mouseDragged(MouseEvent e) {
	                    pointEnd = e.getPoint();
	                    repaint();//update
	                }
	            });
	        }
	        public void paint(Graphics g) {
	            super.paint(g);
	            if (pointStart != null) {
	            	String gettext=txtfd.getText().toString();
	            	if(gettext.equals("Direct Line"))
	            	{
	            		this.directLine(g);
	            	}
	            	else if (gettext.equals("DDA")) {
	            		this.dda(g);
					}
	            	else if (gettext.equals("Bresenham")) {
						this.bresenham(g);
					}
	            	else if (gettext.equals("Bresenham Circle")) {
						this.bresenhamcircle(g);
					}
	            	else if (gettext.equals("Midpoint Circle")) {
						this.midpointcircle(g);
					}
	            }
	        }
			private void midpointcircle(Graphics g) {
				g.setColor(Color.YELLOW);
				System.out.println("Midpoint");
                double b=0,x1,y1,x2,y2;
                x1=(double)pointStart.x;
                y1=(double)pointStart.y;
                x2=(double)pointEnd.x;
                y2=(double)pointEnd.y;
                
                int newx = 0;
                double newyt = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
                int newy = newx+(int)newyt;
                int p = 1-newy;
                while(newx<=newy)
                {
                	
                	g.fillOval((int)(x1+newx+0.5), (int)(y1+newy+0.5), 6, 6);
                	g.fillOval((int)(x1-newx+0.5), (int)(y1+newy+0.5), 6, 6);
                	g.fillOval((int)(x1-newx+0.5), (int)(y1-newy+0.5), 6, 6);
                	g.fillOval((int)(x1+newx+0.5), (int)(y1-newy+0.5), 6, 6);
                	g.fillOval((int)(x1+newy+0.5), (int)(y1+newx+0.5), 6, 6);
                	g.fillOval((int)(x1-newy+0.5), (int)(y1+newx+0.5), 6, 6);
                	g.fillOval((int)(x1-newy+0.5), (int)(y1-newx+0.5), 6, 6);
                	g.fillOval((int)(x1+newy+0.5), (int)(y1-newx+0.5), 6, 6);
                	if(p<0)
                	{
                		p = p+2*newx+3;
                	}
                	else
                	{
                		p = p + 2*(newx-newy)+5;
                		newy--;
                	}
                	newx++;
                	
                }
				
			}
			private void bresenhamcircle(Graphics g) {
				g.setColor(Color.yellow);
                double b=0,x1,y1,x2,y2;
                x1=(double)pointStart.x;
                y1=(double)pointStart.y;
                
                x2=(double)pointEnd.x;
                y2=(double)pointEnd.y;
               
                int newx = 0;
                double newyt = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
                int newy = newx+(int)newyt;
                int d = 3-2*newy;
                while(newx<=newy)
                {
                	
                	g.fillOval((int)(x1+newx+0.5), (int)(y1+newy+0.5), 6, 6);
                	g.fillOval((int)(x1-newx+0.5), (int)(y1+newy+0.5), 6, 6);
                	g.fillOval((int)(x1-newx+0.5), (int)(y1-newy+0.5), 6, 6);
                	g.fillOval((int)(x1+newx+0.5), (int)(y1-newy+0.5), 6, 6);
                	g.fillOval((int)(x1+newy+0.5), (int)(y1+newx+0.5), 6, 6);
                	g.fillOval((int)(x1-newy+0.5), (int)(y1+newx+0.5), 6, 6);
                	g.fillOval((int)(x1-newy+0.5), (int)(y1-newx+0.5), 6, 6);
                	g.fillOval((int)(x1+newy+0.5), (int)(y1-newx+0.5), 6, 6);
                	if(d<0)
                	{
                		d = d+4*newx+6;
                	}
                	else
                	{
                		d = d + 4*(newx-newy)+10;
                		newy--;
                	}
                	newx++;
                	
                }
				
			}
			private void bresenham(Graphics g) {
				g.setColor(Color.yellow);
				System.out.println("bresenham");
                double m=0,b=0,x1,y1,x2,y2;
                x1=(double)pointStart.x;
                y1=(double)pointStart.y;
                
                x2=(double)pointEnd.x;
                y2=(double)pointEnd.y;
                
                m=(y2-y1)/(x2-x1);
                b=y1-(m*x1);
                if(Math.abs(m)<1)
                {
                	if(x1<x2)	
                	{
                		if(y1<y2)
                		{
                			int newx = (int)x1;
                			int newy = (int)y1;
                			int dx = (int)x2-(int)x1;
                			int dy = (int)y2-(int)y1;
                			int dt = 2*(dy-dx);
                			int ds = 2*dy;
                			int d = ds-dx;
                			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			while(newx<x2)
                			{
                				newx++;
                				if(d<0)
                				{
                					d+=ds;
                				}
                				else
                				{
                					newy++;
                					d+=dt;
                				}
                				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			}
                		}
                		else  //y1>y2
                		{
                			int newx = (int)x1;
                			int newy = (int)y1;
                			int dx = (int)x2-(int)x1;
                			int dy = (int)y1-(int)y2;
                			int dt = 2*(dy-dx);
                			int ds = 2*dy;
                			int d = ds-dx;
                			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			while(newx<x2)
                			{
                				newx++;
                				if(d<0)
                				{
                					d+=ds;
                				}
                				else
                				{
                					newy--;
                					d+=dt;
                				}
                				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			}
                		}
                	}
                	
                	else //x1>x2
                	{
                		if(y1>y2)
                		{	
                			int newx = (int)x1;
                			int newy = (int)y1;
                			int dx = (int)x2-(int)x1;
                			int dy = (int)y2-(int)y1;
                			int dt = 2*(dy-dx);
                			int ds = 2*dy;
                			int d = ds-dx;
                			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			while(newx>x2)
                			{
                				newx--;
                				if(d<0)
                				{
                					d-=ds;
                				}
                				else
                				{
                					newy--;
                					d-=dt;
                				}
                				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			}
                		}
                		else  //y1<y2
                		{
                			int newx = (int)x1;
                			int newy = (int)y1;
                			int dx = (int)x2-(int)x1;
                			int dy = (int)y1-(int)y2;
                			int dt = 2*(dy-dx);
                			int ds = 2*dy;
                			int d = ds-dx;
                			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			while(newx>x2)
                			{
                				newx--;
                				if(d>0)
                				{
                					d-=ds;
                				}
                				else
                				{
                					newy++;
                					d+=dt;
                				}
                				g.fillOval((int)(newx+0.5),(int)(newy+0.5), 6, 6);
                			}
                		}
                		
                	}
                }
                
                else //m>1
                {
                	if(y1<y2)	
                	{
                		if(x1<x2)
                		{
                			int newx = (int)x1;
                			int newy = (int)y1;
                			int dx = (int)x2-(int)x1;
                			int dy = (int)y2-(int)y1;
                			int dt = 2*(dx-dy);
                			int ds = 2*dx;
                			int d = ds-dy;
                			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			while(newy<y2)
                			{
                				newy++;
                				if(d<0)
                				{
                					d+=ds;
                				}
                				else
                				{
                					newx++;
                					d+=dt;
                				}
                				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			}
                		}
                	
                		else
                		{
                			int newx = (int)x1;
                			int newy = (int)y1;
                			int dx = (int)x2-(int)x1;
                			int dy = (int)y1-(int)y2;
                			int dt = 2*(dx-dy);
                			int ds = 2*dx;
                			int d = ds-dy;
                			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			while(newy<y2)
                			{
                				newy++;
                				if(d<0)
                				{
                					d+=ds;
                				}
                				else
                				{
                					newx--;
                					d-=dt;
                				}
                				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			}
                		}
                	}
                	
                	else //y1>y2
                	{
                		if(x1>x2)
                		{	
                			int newx = (int)x1;
                			int newy = (int)y1;
                			int dx = (int)x2-(int)x1;
                			int dy = (int)y2-(int)y1;
                			int dt = 2*(dx-dy);
                			int ds = 2*dx;
                			int d = ds-dy;
                			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			while(newy>y2)
                			{
                				newy--;
                				if(d<0)
                				{
                					d-=ds;
                				}
                				else
                				{
                					newx--;
                					d-=dt;
                				}
                				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			}
                		}
                		else  //x1<x2
                		{
                			int newx = (int)x1;
                			int newy = (int)y1;
                			int dx = (int)x1-(int)x2;
                			int dy = (int)y2-(int)y1;
                			int dt = 2*(dx-dy);
                			int ds = 2*dx;
                			int d = ds-dy;
                			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			while(newy>y2)
                			{
                				newy--;
                				if(d>0)
                				{
                					d+=ds;
                				}
                				else
                				{
                					newx++;
                					d+=dt;
                				}
                				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                			}
                		}	
                	}    	
                }
			}
			private void dda(Graphics g) {
				g.setColor(Color.yellow);
				System.out.println("dda");
                double m=0,b=0,newx,newy,x1,y1,x2,y2;
                x1=(double)pointStart.x;
                y1=(double)pointStart.y; 
                newx=(double)pointStart.x;
                newy=(double)pointStart.y;               
                x2=(double)pointEnd.x;
                y2=(double)pointEnd.y;
                
                m=(y2-y1)/(x2-x1);
                b=y1-(m*x1);
                if(Math.abs(m)<1)
                {
                	if(x1<x2)
                	{
                		while(newx!=x2){
                		newx++;
                		newy = newy+m;
                		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                		}
                	}
                	else
                	{
                		while(newx!=x2){
	                		newx--;
	                		newy = newy-m;
	                		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
	                		}
                	}
                }
                else {
                	if(y1<y2)
                	{
                		while(newy!=y2){
                		newy++;
                		newx = newx+1/m;
                		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
                		}
                	}
                	else
                	{
                		while(newy!=y2){
	                		newy--;
	                		newx = newx-1/m;
	                		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
	                		}
                	}
                }
			}
			private void directLine(Graphics g) {
				g.setColor(Color.yellow);
				System.out.println("direct");
		        double m=0,b=0,newx,newy,x1,y1,x2,y2;
		        x1=(double)pointStart.x;
		        y1=(double)pointStart.y;
		        
		        newx=(double)pointStart.x;
		        newy=(double)pointStart.y;
		        
		        x2=(double)pointEnd.x;
		        y2=(double)pointEnd.y;
		        
		        m=(y2-y1)/(x2-x1);
		        b=y1-(m*x1);
		        if(m<1)
		        {
		        	while(newx!=x2){
		        		if(pointStart.x>pointEnd.x)
		        			newx--;
		        		else
		        			newx++;
		        		newy=(m*newx)+b;
		        		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
		        	}
		        }
		        else {
		        	while(newy!=y2){
		        		if(pointStart.y>pointEnd.y)
		        			newy--;
		        		else
		        			newy++;
		        		newx=(newy/m)-(b/m);
		        		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 6, 6);
		        	}
				}
			}
	    };
	    p.add(btPanel);
	    p.add(txtPanel);
	    f.add(p);
	    f.setVisible(true); 
	}

}
