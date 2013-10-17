
(function(){
	console.log ("Function Executed");
	var PREFIXES = ["-webkit-", "-o-", "-ms-", "-moz-", ""];
	window.addEventListener("mousemove", function(e) {
		var obj = document.getElementById("JavaApplet"), string = "", borders = {x: window.innerWidth / 2, y: window.innerHeight / 2}, offsets = {x: 0, y: 0};
		console.log(borders.x / e.clientX, borders.x / e.clientX * 90, borders.x / e.clientX * 90 - 90);

		if (e.clientX < borders.x) offsets.x = e.clientX / borders.x * 90 - 90;
		else offsets.x = (e.clientX - borders.x) / borders.x * 90;

		if (e.clientY < borders.y) offsets.y = 90 - e.clientY / borders.y * 90;
		else offsets.y = - (e.clientY - borders.y) / borders.y * 90;

		for (index in PREFIXES) string += PREFIXES[index] + "transform: rotateY(" + offsets.x + "deg) rotateX(" + offsets.y + "deg);"
		obj.setAttribute("style", string);
	});
})()