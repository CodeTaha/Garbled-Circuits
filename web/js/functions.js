/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function header() // this function calls for the top header
{
    var hed= '<nav class="navbar navbar-default navbar-fixed-top header" role="navigation">' +
  '<div class="container-fluid">' +
    '<!-- Brand and toggle get grouped for better mobile display -->' +
    '<div class="navbar-header">' +
     ' <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">' +
       ' <span class="sr-only">Toggle navigation</span>' +
       ' <span class="icon-bar"></span>' +
       ' <span class="icon-bar"></span>' +
       ' <span class="icon-bar"></span>' +
     ' </button>' +
     '   <a class="navbar-brand" href="index.html"><img id="logo" src="resources/images/vegayanLogo.gif" /></a>' +
    '</div>' +

  
   ' <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">' +
   
      '<ul class="nav navbar-nav topmenu">' +
        '<li><a href="#" onclick=loadFrame("frame1","dashboard.html")>Dashboard</a></li>' +
       ' <li><a href="#" onclick=loadFrame("frame1","second.html")>First</a></li>' +
       ' <li><a href="#" onclick=loadFrame("frame1","second.html")>Second</a></li>' +
       ' <li><a href="sidebarindex.html" >Sidebar index</a></li>' +
        '<li class="dropdown">' +
         ' <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>' +
          '<ul class="dropdown-menu">' +
           ' <li><a href="#">Action</a></li>' +
            '<li><a href="#">Another action</a></li>' +
            '<li><a href="#">Something else here</a></li>' +
            '<li class="divider"></li>' +
           ' <li><a href="#">Separated link</a></li>' +
            '<li class="divider"></li>' +
            '<li><a href="#">One more separated link</a></li>' +
          '</ul>' +
        '</li>' +
      '</ul>' +
      '<form class="navbar-form navbar-left" role="search">' +
       ' <div class="form-group">' +
        '  <input type="text" class="form-control" placeholder="Search">' +
        '</div>' +
        '<button type="submit" class="btn btn-default">Submit</button>' +
      '</form>' +
      '<ul class="nav navbar-nav navbar-right">' +
       ' <li><a href="#">Link</a></li>' +
        '<li class="dropdown">' +
         ' <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>' +
          '<ul class="dropdown-menu">' +
           ' <li><a href="#">Action</a></li>' +
            '<li><a href="#">Another action</a></li>' +
            '<li><a href="#">Something else here</a></li>' +
            '<li class="divider"></li>' +
            '<li><a href="#">Separated link</a></li>' +
          '</ul>' +
        '</li>' +
        
       ' <li class="dropdown user-dropdown">'+
             ' <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i><img src="resources/images/Icon-user.png" width="19" /> Taha Kachwala <b class="caret"></b></a>'+
             ' <ul class="dropdown-menu">'+
            '    <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>'+
             '   <li><a href="#"><i class="fa fa-envelope"></i> Inbox <span class="badge">7</span></a></li>'+
             '   <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>'+
             '   <li class="divider"></li>'+
             '   <li><a href="#" onclick="logout()"><i class="fa fa-power-off"></i> Log Out</a></li>'+
            '  </ul>'+
          '  </li>'+
        
      '</ul>' +
    '</div>' +
  '</div>' +
'</nav>';
    document.write(hed);
   
      
}
function footer()
{
     document.write("<center><p>Copyright <a href='#'>Vegayan Systems</a>. All Rights Reserved.</p></center>");
}

function includes()
{
   var inc=
  '  <title>Simplus</title>' +

    //includes for bootstrap
   ' <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">' +
   ' <link href="resources/css/style.css" rel="stylesheet">' +
   ' <link rel="stylesheet" href="resources/css/jquery-ui.css">' +
   '<link href="resources/css/simple-sidebar.css" rel="stylesheet">'+
   ' <script src="resources/js/jquery.min.js"></script>' +
   ' <script src="resources/bootstrap/js/bootstrap.min.js"></script>' +
 
 '<script src="resources/js/jquery-ui.js"></script>';
 
    document.write(inc);
}

function tabs()
{
    var tabs = [
{ "link" : "#tabs-1" , "title" : "Network Stats" }, 
{ "link" : "#tabs-2" , "title" : "Traffic Plots" }, 
{ "link" : "#tabs-3" , "title" : "Router Stats" }, 
{ "link" : "index_1.html" , "title" : "Delay/Jitter" },
{ "link" : "first.html" , "title" : "first" }
];

var s="<ul>";
for(var i=0; i<tabs.length;i++)
{
    s=s+"<li><a href="+ tabs[i].link +">"+ tabs[i].title +"</a></li>";
}
s=s+"</ul>";
console.log(s);
document.write(s);
}

   function loadFrame( uid, link)
    {console.log("inside loadFrame"+ uid +"**"+link);
        
    $( "#"+ uid).load( link, function( response, status, xhr ) {
  if ( status == "error" ) {
    var msg = "Sorry but there was an error: ";
    $( "#frame1" ).html( msg + xhr.status + " " + xhr.statusText );
  }
});
    }
