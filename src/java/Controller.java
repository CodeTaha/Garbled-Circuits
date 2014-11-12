/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import garbled.Alice;
import garbled.Bob;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Taha
 */
public class Controller extends HttpServlet {
public static Alice alice;
public static Bob bob;
public static boolean alice_assigned=false;
Gson gson=new Gson();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {
        PrintWriter out = response.getWriter();
        
        int fn=Integer.parseInt(request.getParameter("fn"));
            
           switch(fn)
           {/*
               1: Initializes Alice
               2: Checks for BOB if alice is set. If yes then sends the truth Table
               3: Bob sends input
               */
               case 1:{try {
                   int alice_input=Integer.parseInt(request.getParameter("input"));
                   alice=new Alice(alice_input);
            alice.generateTable();
            //System.out.println("truthtable= "+alice.truthTable);
            alice_assigned=true;
                   out.println(gson.toJson(alice.truthTable));
                                } catch (NoSuchPaddingException ex) {
                                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (InvalidKeyException ex) {
                                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoSuchAlgorithmException ex) {
                                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IllegalBlockSizeException ex) {
                                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (BadPaddingException ex) {
                                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        }break;
               case 2:  {
                   
                        if(alice_assigned)
                        {
                            out.println(gson.toJson(alice.truthTable)+"KK"+gson.toJson(alice.Alice_key));
                            bob=new Bob(alice);
                            //out.println("taha");
                        }
                        else
                        {
                            out.println("");
                        }
                        }break;
               case 3:    {
            try {
                int bob_input=Integer.parseInt(request.getParameter("input"));
                bob.initiate_OT(bob_input);
                
                byte temp[]=alice.gk(bob.gbi());
                
                out.println(gson.toJson(bob.Randoms)+"KK"+gson.toJson(bob.k)+"KK"+gson.toJson(Alice.publicKey)+"KK"+gson.toJson(Alice.n)+"KK"+gson.toJson(bob.v)+"KK"+gson.toJson(alice.m0)+"KK"+gson.toJson(alice.m1)+"KK"+gson.toJson(bob.preKb0)+"KK"+gson.toJson(bob.preKb1)+"KK"+gson.toJson(temp));
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
                        }break;
            
           }
    }
            
       
      
       
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
 
   
}
