import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.regex.*;

class Notepad extends WindowAdapter implements ActionListener,KeyListener{
     Frame f;
     TextArea ta;
     TextField tf,rf,rr;
     Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
     Dialog d,fndlg,rdlg,savedlg;
     int index=-1;
     String pr,sname;
     int tn,saved=0,flag1=0,flag2=0,flag3=0,tvc=0,kp=0;
     
      public Notepad()
      {
              f=new Frame();
              f.setSize(300,400);
              ta=new TextArea();
            
              ta.addKeyListener(this);
              f.add(ta);
              f.setVisible(true);
              f.addWindowListener(this);
                
              MenuBar mBar = new MenuBar();
              f.setMenuBar(mBar);
              Menu m1=new Menu("File");
              Menu m2=new Menu("Edit");
              mBar.add(m1);
              mBar.add(m2);

              MenuItem mi1=new MenuItem("New");
              
              MenuItem mi2=new MenuItem("Open");
              MenuItem mi3=new MenuItem("Save");
              MenuItem mi4=new MenuItem("Save as");
              MenuItem mi5=new MenuItem("Exit");
              MenuItem mi6=new MenuItem("Find");
              MenuItem mi7=new MenuItem("Replace");
             m1.add(mi1);
             m1.add(mi2);
             m1.add(mi3);
             m1.add(mi4);
             m1.addSeparator(); 
             m1.add(mi5);
             m2.add(mi6);
             m2.add(mi7);
             mi1.addActionListener(this);
               
             mi2.addActionListener(this);
             mi3.addActionListener(this);
             mi4.addActionListener(this);
             mi5.addActionListener(this);
             mi6.addActionListener(this);
             mi7.addActionListener(this);
             d = new dlg(f , "ERROR",true);
                 
             d.add( new Label ("Not Found."));  
                
             d.setSize(100,100);    
             d.setResizable(false);
             d.addWindowListener(this);


   ///FILE DIALOG/////

             fndlg=new Dialog(f,"Find");
             fndlg.setLayout(new BorderLayout());
             fndlg.add(new Label("Find"),"North");
             tf=new TextField();
             tf.addTextListener(this);
             fndlg.add(tf,"Center");
             fndlg.setSize(250,130);
             fndlg.setResizable(false);
              Panel p=new Panel();
              b1=new Button("FindNext");
              b2=new Button("Close");
              b4=new Button("replace");
              b5=new Button("ReplaceAll");
              b3=new Button("FindNext");
              b6=new Button("Close");
              b1.setEnabled(false);
              b3.setEnabled(false);
              b4.setEnabled(false);
              b5.setEnabled(false);
              p.add(b1);
              p.add(b2);
              fndlg.add(p,"South");
              b1.addActionListener(this);
              b2.addActionListener(this);
              b3.addActionListener(this);
              b4.addActionListener(this);
              
              b5.addActionListener(this);
              
              b6.addActionListener(this);
             fndlg.addWindowListener(this);



             //replace dlg
             rdlg =new Dialog(f,"Replace");
             rdlg.setLayout(new BorderLayout());
             Panel rp=new Panel();
            rp.setLayout(new BorderLayout());
             rr=new TextField();
              rf=new TextField();
              rf.addTextListener(this);
             rp.add(new Label("Find"),"North");
             rp.add(rf,"Center");
             Panel rp2=new Panel();
             
            rp2.setLayout(new BorderLayout());
             rp2.add(new Label("Replace"),"North");
             rp2.add(rr);
             rdlg.add(rp,"North");
             rdlg.add(rp2,"Center");
             Panel pp=new Panel();
             pp.setLayout(new GridLayout(1,4,12,12));
             pp.add(b3);
             pp.add(b4);
             pp.add(b5);
             pp.add(b6);
             rdlg.add(pp,"South");

             rdlg.setSize(280,150);
             rdlg.setResizable(false);
             rdlg.addWindowListener(this);


         //////Save dlg
            savedlg=new Dialog(f,"Notepad");

              savedlg.setLayout(new BorderLayout());
              savedlg.add(new Label("Do you want to save changes to untitled?"));
              Panel ppp=new Panel();
              savedlg.setSize(300,120);
              savedlg.setResizable(false);
              ppp.setLayout(new GridLayout(1,3,10,10));
              b7=new Button("Save.");
              b8=new Button("Donot Save");
              b9=new Button("Cancel");
              ppp.add(b7);
              ppp.add(b8);
              ppp.add(b9);
              savedlg.add(ppp,"South");
              b7.addActionListener(this);
              b8.addActionListener(this);
              b9.addActionListener(this);
              savedlg.addWindowListener(this);
              savedlg.setVisible(false);
      }
      public boolean open()
      {
        FileDialog fd = new FileDialog(f,"Select File",0);
        fd.setVisible(true);
        if(fd.getFile()!=null)
        {
        try{
              
          sname=fd.getDirectory()+fd.getFile();
        FileInputStream fis=new FileInputStream(fd.getDirectory()+fd.getFile());
        int c=0;
        String st="";
        while((c=fis.read())!=-1){    
            st+=(char)c;
        }    
        ta.setText(st);
        fis.close(); 
        retn true;
        }
        catch(Exception et)
        {
         d.setVisible(true); 
         return false;
        }
       }
       else
       return false;
      }
      public boolean save()
      {
        FileDialog fd = new FileDialog(f,"Save as",1);
        fd.setVisible(true);
         //System.out.println(fd.getFile());
         if(fd.getFile()!=null)
         {try{
                 sname=fd.getDirectory()+fd.getFile();
          FileOutputStream fos=new FileOutputStream(fd.getDirectory()+fd.getFile());
          
          String st=ta.getText();
       //   System.out.println(st);
          byte b[]=st.getBytes();   
          fos.write(b);    
      
          fos.close();     
          return true;
          }
          catch(Exception et)
          {
           d.setVisible(true); 
           return false;
          }
         }
         else
         return false;
      }
      public void saved(){
                      
              if(saved==1)
              {
                try{
                  
                     FileOutputStream fos=new FileOutputStream(sname);
           
                       String st=ta.getText();
        
                       byte b[]=st.getBytes();   
                           fos.write(b);    
                           saved=1;
                            fos.close();     
          
                    }
                        catch(Exception et)
                          {
                              d.setVisible(true); 
            
                             }
              }
              else
              {
                      save();
              }
      }
       public void check()
       {
           if(flag1==1)
           {
             if(open()){
             saved=1;
             }flag1=0;
             
           }
           else if(flag2==1)
           {
             
            ta.setText("d");
            ta.setText("");
            flag2=0;
            saved=0;
           }
           else if(flag3==1)
           {
             System.exit(0);
             
           }
           kp=0;
          
       }


      public void actionPerformed(ActionEvent e)
      {
        String s=e.getActionCommand();
         if(s=="New")
         {   
          if(kp==1)
          {
            savedlg.setVisible(true);
            flag2=1;
          }
          else
          {
             ta.setText("d");
             ta.setText("");
          }
           
          }
         else if(s=="Open")
         {   
               if(kp==1)
               {
                 savedlg.setVisible(true);
                 flag1=1;
               }
               else
               {
                  if(open())
                  {
                         saved=1;
                  }
               }
         }
         else if(s=="Save")
         { System.out.println(saved+"sdasd"+kp);
            if(saved==1 && kp==1)
            {
              saved();
              kp=0;
            }
            else if(saved==0 && kp==1)
            {
               if(save()){
              saved=1;
              kp=0;
                   }
              
            }
         }
         else if(s=="Save as")
         {
          if(kp==1)
          {
            savedlg.setVisible(true);
          }
          else
          {
             if(save())
             {
                 saved=1;
             }
             else{

             }
          }
         }
         else if(s=="Save.")
         {   savedlg.setVisible(false);
          
              if(saved==1)
             {
               saved();
              
                check();
             }
             else
             {
                 if(save())
                 {
                    check();
                 }
             }
         }
         else if(s=="Donot Save")
         {  savedlg.setVisible(false);
               check();
         }
         else if(s=="Exit")
         {
           
          if(kp==1)
          {
            savedlg.setVisible(true);
            flag3=1;
          }
          else
          {
            System.exit(0);
          }
         }
         else if(s=="Find")
         { 
            fndlg.setVisible(true);
             index=ta.getCaretPosition();
             System.out.println(index);
             tn=0;
         }
         else if(s=="Replace")
         {
              rdlg.setVisible(true);
              index=ta.getCaretPosition();
              
              tn=1;
         }
         else if(s=="FindNext")
         {
          String pattern;
          int cot=0;
          int start=-1,end=-1;
          start=ta.getSelectionStart();
          end=ta.getSelectionEnd();
          if(start==end)
          {
            index=ta.getCaretPosition();
            for(int i=0;i<ta.getText().length()&&i<index;i++){
              if(ta.getText().charAt(i)=='\r')
              cot++;
            }
            System.out.println(cot);
            index=index-cot;
          }
             if(tn==0)
             {
               pattern=tf.getText();
             }
             else
             { 
               pattern=rf.getText();
             }
             
           if(pr==null)
             pr=pattern;
             if(!pattern.equals(pr))
             {
               index=ta.getCaretPosition();
               pr=pattern;
               System.out.println(pattern);
             }
        //////
        
        System.out.println(index);
        String match=ta.getText().replaceAll("\r\n", "\n");
        if(pattern != null)
        {
           String tSearch = match;
           
           boolean endOfSearch = false;
           while(!endOfSearch)
           {
              int in = 0;
                 in = tSearch.indexOf(pattern, index);
               
              if(in != -1)
              {
                 endOfSearch = true;
                 ta.select(in, in + pattern.length());
                 index = in+pattern.length();
                 ta.requestFocus();
              }
              else
              {
                 endOfSearch = true;
                 d.setVisible(true);
              }
           }
        }
            ////



         
         }
         else if(s=="replace")
         {
             int start=-1,end=-1;
             start=ta.getSelectionStart();
             end=ta.getSelectionEnd();
             String stoR=rr.getText();
             String st=ta.getText();
             StringBuffer sb=new StringBuffer(st);
             if(start!=end)
             { 
                 sb.replace(start,end,stoR);
                 ta.setText(sb.toString());
                 ta.setCaretPosition(start);

             }
             else
             {
               d.setVisible(true);
             }
         }
         else if(s=="ReplaceAll")
         {
          String pattern=rf.getText();
           index=0;
           String stoR=rr.getText();
        String match=ta.getText().replaceAll("\r\n", "\n");
        if(pattern != null)
        {
           String tSearch = match;
           StringBuffer sb=new StringBuffer(tSearch);
                        
           boolean endOfSearch = false;
           while(!endOfSearch)
           {
              int in = 0;
                 in = tSearch.indexOf(pattern, index);
               System.out.println(index);
              if(in != -1)
              {
                 ta.select(in, in + pattern.length());
                 index = in+pattern.length();
                            
                    sb.replace(in,in+pattern.length(),stoR);
                        tSearch=sb.toString();


              }
              else
              {
                 endOfSearch = true;
                 
              }
           }
           ta.setText(tSearch);
        }
            ////




          
         }
         else if(s=="Close")
          {
            fndlg.setVisible(false);
            rdlg.setVisible(false);
            index=ta.getCaretPosition();
            pr=null;
          }
          else if(s=="Cancel")
          {
            savedlg.setVisible(false);
            flag2=0;
            flag1=0;
          }
      }
      public void windowClosing(WindowEvent we)
      { 
          if(we.getSource()==f)
          {
            if(kp==1)
            {
              savedlg.setVisible(true);
              flag3=1;
            }
            else
            {
              System.exit(0);
            }
          }
          else if(we.getSource()==d)
          {
            d.setVisible(false);
            //index=ta.getCaretPosition();
          }
          else if(we.getSource()==fndlg)
          {
            fndlg.setVisible(false);
            index=ta.getCaretPosition();
            pr=null;
            index=0;
          }
          else if(we.getSource()==rdlg)
          {
            rdlg.setVisible(false);
            index=ta.getCaretPosition();
            pr=null;
            index=0;
          }
          else if(we.getSource()==savedlg)
          {
            savedlg.setVisible(false);
          }
      }
      public void textValueChanged(TextEvent te)
      {       if(te.getSource()==t)
              {
                  
              }
             else if(te.getSource()==tf)
             { 
                 if(tf.getText().length()>0)
                 {
                     b1.setEnabled(true);
                 }
                 else
                 {
                   b1.setEnabled(false);
                 }
             }
             else if(te.getSource()==rf)
             {
               
              if(rf.getText().length()>0)
              {
                  b3.setEnabled(true); b4.setEnabled(true); b5.setEnabled(true);
              }
              else
              {
                
                b3.setEnabled(false); b4.setEnabled(false); b5.setEnabled(false);
              }
             }
      }
 public void keyTyped(KeyEvent e) {
    kp=1;
  }
      
      public static void main(String args[])
      {
          new Notepad();
      }

 


  
}