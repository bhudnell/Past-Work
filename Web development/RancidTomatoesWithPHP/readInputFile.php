<!DOCTYPE html>
<html>
<head>
<title>Part 1</title>
<meta charset="utf-8" />
</head>
<?php
// Read data from input file and establish some local variables 
// $movie could be tmnt, tmnt2, mortalkombat, princessbride (see the folders
// in this project), or a new one.
//
// These PHP variables can be used anywhere below in a PHP block.
//
$movie = "princessbride";
$imageFileName = $movie . "/overview.png";
$overviewTextFileName = $movie . "/overview.txt";
?>
<body>
	<img src="images/rancidbanner.png" alt="Rancid Tomatoes">
	
	<img src="<?= $imageFileName ?>" alt="Overview image">
   
  <?php
		print ("<br><br>") ;
		// For each line in the overview.txt file, print the heading
		// such as STARRING, DIRECTOR, PRODUCER, ... followed by all
		// text that follow "heading:" followed by a "<br>".  You will
		// need to process the overview.txt file line by line. 
		// The heading is always the first part of a line and is always
		// followed by a colon ':'.  You need nested for loops.
  ?>
</body>
</html>