(function(){
    var filesModule = angular.module('files',[]);
    var filesController = filesModule.controller('FilesController',function(){
        this.files = [
            {url:'#picture', name: 'MyFile.jpg', size:'5000KB', date:'2014-01-01', tags:['tag1', 'tag2']},
            {url:'#picture', name: 'MyFile.jpg', size:'5000KB', date:'2014-01-01', tags:['tag1', 'tag2']},
            {url:'#picture', name: 'MyFile.jpg', size:'5000KB', date:'2014-01-01', tags:['tag1', 'tag2']},
            {url:'#picture', name: 'MyFile.jpg', size:'5000KB', date:'2014-01-01', tags:['tag1', 'tag2']}
        ]; 
    
    });
    
 
 })();