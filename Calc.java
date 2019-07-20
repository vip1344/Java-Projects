import java.awt.*;
import java.awt.event.*;
class Calc extends WindowAdapter implements ActionListener {
     Panel p1,p2;
     TextField t1;
     Button b[];
     Frame f;
      double m=0;
      int flag=0,flag2=0;
      String op="b";
    public Calc() throws Exception{
        f = new Frame();
        f.setSize(400, 400);
        f.addWindowListener(this);
        p1=new Panel();
        p2=new Panel();
        p1.setLayout(new GridLayout(4,4));
        p2.setLayout(new GridLayout(1,1));
         
       t1 = new TextField("0",1);
        t1.setEditable(false);
         b=new Button[16];
        int c=1;
         for(int i=0;i<12;i++){

         if(i==3)
         {
             b[i]=new Button("+");
         }
         else if(i==7)
          b[i]=new Button("-");
          else if(i==11)
          b[i]=new Button("*");
          else
          {
            String str=c+"";
         b[i]=new Button(str);
         c++;
        }    
        }
        b[12]=new Button("C");
        b[13]=new Button("0");   
        b[14]=new Button("/");    
        b[15]=new Button("=");
        for(int i=0;i<16;i++)
         {p1.add(b[i]);
         b[i].addActionListener(this);
        }
        
         p2.add(t1);
         f.add(p2,"North");
         f.add(p1,"Center");
          f.setVisible(true);
    }
     public void windowClosing(WindowEvent e)
     {
         f.dispose();
     }
     public void actionPerformed(ActionEvent e)
     {  //System.out.println("SDAsdasdadsadsa");
         Button b1=(Button)e.getSource();
          String s=b1.getLabel();
          String ans=t1.getText();
         // System.out.println(ans);
          double a=0, b=0;
          a=Double.parseDouble(ans);
          if(s=="+" || s=="-" || s=="*" || s=="/" )
          {
              if(op!="b" && flag2==1)
              {

                double c=0;
                if(op=="+")
                {
                     c=m+a;
                    
                    
                }
                else if(op=="-")
                {
                   c=m-a;
                   
                } 
                else if(op=="*")
                {
                    c=(double)m*a;
            
                }
                else if(op=="/")
                {
                    
                    c=(double)m/a;
        
                }
               if(c!=0)
               { 
                if(c %1==0)
                 ans=(int)c+"";
                 else
                 ans=c+"";}
            
                m=c;
                flag=0;
                flag2=0;
               }
              else if(op=="b")
              {

               m=a;
             
              }
              
              op=s;
          }
          else if(s=="C")
          {
              m=0;
              op="b";
              ans="0";
              flag=0;
              flag2=0;
          }
          else if(s=="=")
          {  double c=0;
                if(op=="+")
                {
                     c=m+a;
                    
                    
                }
                else if(op=="-")
                {
                   c=m-a;
                   
                } 
                else if(op=="*")
                {
                    c=(double)m*a;
            
                }
                else if(op=="/")
                {
                    
                    c=(double)m/a;
        
                }
               if(c!=0)
               { 
                if(c %1==0)
                 ans=(int)c+"";
                 else
                 ans=c+"";}
                op="b";
                m=0;
                flag=0;
          }
          else
          {   if(op=="b")
             {
              if(a==0){
             // System.out.println("dsadsaasddsasda");
                ans=s;}
                else
                ans+=s;
              }
              else
              {
                  if(flag==0)
                  {ans=s;
                   flag=1;
                   flag2=1;
                   }
                   else
                   {
                       ans+=s;
                       flag2=1;
                   }
              }
          }
          t1.setText(ans);
     }
    public static void main(String args[]) throws Exception{
    
        Calc c = new Calc();
         
    }
}