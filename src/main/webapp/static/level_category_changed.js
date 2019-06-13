var level0 = document.getElementById("level0");
var level1 = document.getElementById("level1");
var level2 = document.getElementById("level2");
window.onload = function() {
	var xmlRequest = new XMLHttpRequest();
	xmlRequest.open("get", path+"/JSONCategoryLevel0",
			true);
	xmlRequest.send(null);
	xmlRequest.onreadystatechange = function() {
		if (xmlRequest.readyState == 4 && xmlRequest.status == 200) {

			var json = xmlRequest.responseText;
			//console.log(json);
			var js = eval("(" + json + ")");
			for (var i = 0; i < js.length; i++) {
				var op = document.createElement("option");
				op.value = js[i].categoryName;
				var text = document.createTextNode(js[i].categoryName);
				op.appendChild(text);
				level0.appendChild(op);
			}

		}
	};
};

function level0CategoryChanged() {

	var xmlRequest = new XMLHttpRequest();

	xmlRequest.open("post", "/ShopDemoSSM/JSONCategoryLevel1",
			true);
	xmlRequest.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlRequest.send("level0=" + level0.value);
	console.log("level0CategoryChanged level0= "+ level0.value);
	xmlRequest.onreadystatechange = function() {

		if (xmlRequest.readyState == 4) {
			var json = xmlRequest.responseText;
			
			var js = eval("(" + json + ")");

			level1.options.length = 0;
			var optemp = document.createElement("option");
			var texttemp = document.createTextNode("        ");
			optemp.appendChild(texttemp);
			level1.appendChild(optemp);
			
			
			for (var i = 0; i < js.length; i++) {
				var op = document.createElement("option");
				op.value = js[i].categoryName;
				console.log(js[i].categoryName);
				var text = document.createTextNode(js[i].categoryName);
				op.appendChild(text);
				level1.appendChild(op);
			}
			level1CategoryChanged();
			
		}

	}
	
}
function level1CategoryChanged() {
	var xmlRequest = new XMLHttpRequest();

	xmlRequest.open("post", "/ShopDemoSSM/JSONCategoryLevel2",
			true);
	xmlRequest.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlRequest.send("level1=" + level1.value);
	console.log("level1CategoryChanged level1= "+ level1.value);
	xmlRequest.onreadystatechange = function() {

		if (xmlRequest.readyState == 4) {
			var json = xmlRequest.responseText;			
			var js = eval("(" + json + ")");

			level2.options.length = 0;
			var optemp = document.createElement("option");
			var texttemp = document.createTextNode("        ");
			optemp.appendChild(texttemp);
			level2.appendChild(optemp);

			for (var i = 0; i < js.length; i++) {
				var op = document.createElement("option");
				op.value = js[i].categoryName;					
				console.log(js[i].categoryName);
				var text = document.createTextNode(js[i].categoryName);
				op.appendChild(text);
				level2.appendChild(op);
			}
			level2CategoryChanged();
		}

	}
	
}
function level2CategoryChanged() {
}