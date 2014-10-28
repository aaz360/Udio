var dialog, form

$(document).ready(function(){    
    initDialog();    
    $("#upload").click(function(){        
        dialog.dialog("open");        
    });    
});

function initDialog(){
    dialog = $( "#dialog-form" ).dialog({
    autoOpen: false,
    height: 470,
    width: 450,
    modal: true,    
    close: function() {
        form = dialog.find("form" );
        form[ 0 ].reset();        
        $("#files").empty();
    }
  });  
}

(function(){
    var app = angular.module('main',['fileUpload','files', 'tags']);
})();

