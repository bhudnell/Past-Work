<?php
include 'functions.php';

$movie = "princessbride";
if (isset ( $_POST ["film"] ))
	$movie = $_POST ["film"];

$imageFileName = $movie . "/overview.png";
$filename = "./" . $movie . "/info.txt";
$overviewTextFileName = $movie . "/overview.txt";

list ( $name, $year, $rating ) = file ( $filename );
trim ( $name );
trim ( $year );
trim ( $rating );
?>
<!DOCTYPE html>
<html>
<head>
<title><?php echo trim(file($movie . "/info.txt")[0])?></title>
<meta charset="utf-8" />
<link href="movies.css" type="text/css" rel="stylesheet" />
</head>

<body>
	<div id="banner">
		<img src="images/rancidbanner.png" alt="Rancid Tomatoes">

	</div>

	<h1><?php echo($name . " (" . $year . ")")?></h1>

	<form action="./movie.php" method="post">
		<select name="film">
			<?php
			$movies = glob ( './*', GLOB_ONLYDIR );
			foreach ( $movies as $ele => $file ) {
				if ($file !== "./images") {
					$title = file ( $file . "/info.txt" );
					echo "<option value=" . $file . ">" . trim ( $title [0] ) . "</option>";
				}
			}
			?>
		</select> <input type="submit" value="Get Movie Overview">
	</form>
	<br />

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

		<div class="contentarea">
			<?php
			$count = 0;
			$arr = scandir ( "./" . $movie );
			foreach ( $arr as $review ) {
				$pos = strpos ( $review, "review" );
				if ($pos === false) {
				} 
				else {
					if ($count % 2 == 0){
						echo "<div class=split>";
						echo oneReview ( "./" . $movie . "/" . $review );
					}else
						echo oneReview ( "./" . $movie . "/" . $review ) . "</div>";
					$count ++;
				}
			}
			if ($count%2 == 1)
				echo "</div>";
			?>
		</div>

		<div class='overview'>
			<dl>
  			<?php
					$arr = file ( $overviewTextFileName );
					for($line = 0; $line < count ( $arr ); $line ++) {
						$contents = explode ( ":", $arr [$line] );
						echo ("<dt>" . $contents [0] . "</dt>\n<dd>");
						if (in_array ( $contents [0], array (
								"SCREENWRITER",
								"STARRING",
								"PRODUCER",
								"DISTRIBUTORS",
								"GENRE" 
						) )) {
							$contents = explode ( ",", $contents [1] );
							for($word = 0; $word < count ( $contents ); $word ++)
								echo ($contents [$word] . "<br/>");
						} else
							echo ($contents [1] . "<br/>");
						echo ("</dd>\n");
					}
					?>
			</dl>
		</div>
		<p id='reviewnums'>(1-<?php echo $count; ?>) of <?php echo $count; ?></p>
	</div>
</body>
</html>