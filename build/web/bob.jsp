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
        <title>BOB</title>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <style>
           table{
    border: 1px solid black;
    table-layout: fixed;
    width: 600px;
    font-family: Arial;
    font-size: 14px;
}
             th, td {
    border: 1px solid black;
    //overflow: hidden;
    width: 200px;
    word-wrap: break-word;
    padding:6px;
}
        </style>
    </head>
    <body id='bob_body'>
        <h1>This is Bob</h1>
        <h2>Garbled Truth table received from Alice</h2>
        <table id='truth_table'>
            
        </table>
        <div id='Alice_input_key'></div>
        <br/>
        <div id='OT'>
            <label>Enter Bob's Input (0/1): </label><input type="number" name="b_ip" id="b_ip" value='1'/>
            <button id='submit_OT'> Initiate Oblivious Transfer</button>
        </div>
    </body>
    <script>
        var interval;
        var bi;
         $(document).ready(function(){
            $("#OT").hide();
            interval=setInterval(checkIfAliceSet(), 300);
            
            
            
            $("#submit_OT").click(function(e){
                 initiate_OT();  
        });
         });
        function checkIfAliceSet()
        {
           
              $.ajax({
           type: "POST",       // the dNodeNameefault
           url: "alice_controller",
           data:   {fn:2},
           success: function(data){
               if(data.length>5)
               {
                   data=data.split("KK")
                   
                   var truthtable=JSON.parse(data[0]);
                   console.log(truthtable);
                   construct_truthTable(truthtable);
                   var Alice_key=JSON.parse(data[1]);
                   $("#Alice_input_key").append("<h3>Alice's Key= "+Alice_key['key']+"</h3>");
                   $("#OT").show();
               }
               
            }
                            });
        }
        
        function construct_truthTable(truthtable)
        {
            $("#truth_table").append("<tr><th>Alice Input</th><th>Bob's Input</th><th>Output</th></tr>");
            for(var i=0;i<truthtable.length;i++)
            {
                $("#truth_table").append("<tr id='row_"+i+"'></tr>");
                for(var j=0; j<2;j++)
                {
                $("#row_"+i).append("<td>"+truthtable[i][j]['key']+"</td>");    
                }
                $("#row_"+i).append("<td>"+truthtable[i][2]+"</td>");    
          
               
            }
        }
        
        function initiate_OT()
        {
            bi=$("#b_ip").val();
            
                    $.ajax({
           type: "POST",       // the dNodeNameefault
           url: "alice_controller",
           data:   {fn:3, input:$("#b_ip").val()},
           success: function(data){
                data=data.split("KK");
                console.log(data[2]);
               var Randoms=JSON.parse(data[0]);
               console.log(Randoms);
                $("#OT").append("<br><br><h2>Step 1</h2><h3>Random Numbers  and RSA's (n,e) Received from Alice <br/></h3> <b>R0=</b>["+Randoms[0]+"] <br/><b> R1=</b>["+Randoms[1]+"] <br/><b>Alice's RSA Public Key e=</b>"+data[2]+" <b> and n=</b>"+data[3]+"<br><h2>Step 2</h2><h3>Bob generates Random value 'k', calculates 'v' and sends 'v' to Alice</h3><b> k =</b>"+data[1] );
                 $("#OT").append("<br>v="+data[4]);
                 $("#OT").append("<br><h2>Step 3</h2><h3>mo, m1 received from Alice</h3><b>m0=</b>"+data[5]+" <br><b>m1=</b>"+data[6]);
                 var k1,k2;
                 if(bi===0)
                 {
                     k1=data[9];
                     k2=data[8];
                 }
                 else
                 {
                     k1=data[7];
                     k2=data[9];
                 }
                 $("#OT").append("<br><h2>Step 4</h2><h3>Bob computes possible keys p_Kb0 and p_Kb1</h3><b>Possible keys for BOB</b><br> <b>p_Kb0=</b>"+k1+"<br><b>p_Kb1=</b>"+k2);
                
            }
                            });
                        } 
        
    </script>
</html>
