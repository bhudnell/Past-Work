<?php
function oneReview($fileName) {
	$lines = file($fileName);
	
	echo "<div class='onereview'><p class='review'>";
	if (trim($lines[1]) === "ROTTEN")
		echo "<img src='images/rotten.gif' alt='Rotten' />";
	else
		echo "<img src='images/fresh.gif' alt='Fresh' />";
	echo "<q>" . $lines[0] . "</q></p><p class='reviewer'>";
	echo "<img src='images/critic.gif' alt='Critic' />" . $lines[2] .  "<br />";
	echo $lines[3] . "</p></div>";
}