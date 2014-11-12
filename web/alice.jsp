<%-- 
    Document   : alice
    Created on : 20 Oct, 2014, 10:30:56 PM
    Author     : Taha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alice</title>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    </head>
    <body>
        <h1>This is Alice</h1>
        <label>Enter Alice's Input (0/1):</label><input type="number" name="a_ip" id="a_ip"/>
        <button id='submit_input'> Submit</button>
    </body>
    <script>
        $(document).ready(function(){
            $("#submit_input").click(function(e){
                  
                    $.ajax({
           type: "POST",       // the dNodeNameefault
           url: "alice_controller",
           data:   {fn:1, input:$("#a_ip").val()},
           success: function(data){
               //console.log(data);
            }
                            });
                        } );
        });
        
    </script>
</html>
