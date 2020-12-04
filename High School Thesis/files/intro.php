<!doctype html>
<html>
<style>
body {
	overflow: hidden;
}
img {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	height: auto;
	width: 50%;
}

@keyframes anim {
	from{
		height: 0px;
		width: 0px;
	}
	to {
		height: 1500px;
		width: 1500px;
	}
}

#ball {
	height: 0px;
	width: 0px;
	border-radius: 100%;
	background-color: black;
	animation: anim 3s forwards;
}

</style>
<img src="/imagini/Sigla Decupat Mare.png"/>
<div id = "ball"/>
</html>