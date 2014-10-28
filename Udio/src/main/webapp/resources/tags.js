(function(){
    var tagsModule = angular.module('tags',[]);
    var tagsController = tagsModule.controller('TagsController',function(){
        this.tags = [
            {url:'#1241234', name: 'Movies'},
            {url:'#1241234', name: 'Music'},
            {url:'#1241234', name: 'Mp3'},
            {url:'#1241234', name: 'Pictures'}            
        ];     
    });    
 
 })();