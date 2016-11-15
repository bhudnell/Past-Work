<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="movies.css">
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
$movie = $_GET["film"];
$imageFileName = $movie . "/overview.png";
$filename = "./" . $movie . "/info.txt";
$overviewTextFileName = $movie . "/overview.txt";

list ( $name, $year, $rating ) = file ( $filename );
$name = str_replace ( "\n", "", $name );
$year = str_replace ( "\n", "", $year );
$rating = str_replace ( "\n", "", $rating );

?>
<body>
	<div id="banner">
		<img src="images/rancidbanner.png" alt="Rancid Tomatoes">
	</div>

	<h1><?php echo($name . " (" . $year . ")")?></h1>

	<div class='overallarea'>
		<div class='contentarea rottenbig'>
		<?php
		if ($rating < 60)
			echo ("<img src='./images/rottenlarge.png' alt='Rotten' class='rottenbig' />");
		else
			echo ("<img src='./images/freshlarge.png' alt='Fresh' class='rottenbig' />");
		echo ($rating . "%");
		?>
		</div>

		<div>
			<img src="<?= $imageFileName ?>" alt="Overview image"
				id="overviewimage">
		</div>
		
		<div class='overview'>
			<dl>
  <?php
		$arr = file ( $overviewTextFileName );
		for($line = 0; $line < count ( $arr ); $line ++) {
			$contents = explode ( ":", $arr [$line] );
			echo ("<dt>" . $contents [0] . "</dt>\n<dd>");
			if (in_array($contents[0], array("SCREENWRITER", "STARRING", "PRODUCER", "DISTRIBUTORS", "GENRE"))){
				$contents = explode ( ",", $contents [1] );
				for($word = 0; $word < count ( $contents ); $word ++)
					echo ($contents [$word] . "<br/>");
			}
			else
				echo ($contents [1] . "<br/>");
			echo ("</dd>\n");
		}
		?>
			</dl>
		</div>
	</div>
</body>
</html>