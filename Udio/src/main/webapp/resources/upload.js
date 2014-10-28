(function(){
var uploadModule = angular.module('fileUpload', ['angularFileUpload']);

 var UploadController = uploadModule.controller('UploadController',[ '$scope', '$upload', function($scope, $upload) {
    $scope.uploadRightAway = false;
   
    $scope.onFileSelect = function($files) {        
		$scope.selectedFiles = [];
		$scope.progress = [];
		if ($scope.upload && $scope.upload.length > 0) {
			for (var i = 0; i < $scope.upload.length; i++) {
				if ($scope.upload[i] != null) {
					$scope.upload[i].abort();
				}
			}
		}
		$scope.upload = [];
		$scope.uploadResult = [];
		$scope.selectedFiles = $files;
		$scope.dataUrls = [];
		for ( var i = 0; i < $files.length; i++) {
			var $file = $files[i];
			if ($scope.fileReaderSupported && $file.type.indexOf('image') > -1) {
				var fileReader = new FileReader();
				fileReader.readAsDataURL($files[i]);
				var loadFile = function(fileReader, index) {
					fileReader.onload = function(e) {
						$timeout(function() {
							$scope.dataUrls[index] = e.target.result;
						});
					}
				}(fileReader, i);
			}
			$scope.progress[i] = -1;
			if ($scope.uploadRightAway) {
				$scope.start(i);
			}
		}
	 };
    
    $scope.start = function(index) {
		$scope.progress[index] = 0;
		$scope.errorMsg = null;
		
        $scope.upload[index] = $upload.upload({
				url: "//localhost:8080/upload",
				method: "POST",
				headers: {'my-header': 'my-header-value'},
                withCredentials: true,
				data : {
					tags : $scope.tags,
					errorCode: $scope.generateErrorOnServer && $scope.serverErrorCode,
					errorMessage: $scope.generateErrorOnServer && $scope.serverErrorMsg
				},				
				file: $scope.selectedFiles[index]				
			});
			$scope.upload[index].then(function(response) {
				$timeout(function() {
					$scope.uploadResult.push(response.data);
				});
			}, function(response) {
				if (response.status > 0) $scope.errorMsg = response.status + ': ' + response.data;
			}, function(evt) {
				// Math.min is to fix IE which reports 200% sometimes
				$scope.progress[index] = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			});
			$scope.upload[index].xhr(function(xhr){
//				xhr.upload.addEventListener('abort', function() {console.log('abort complete')}, false);
			});
		}
    
}]);
})();
    