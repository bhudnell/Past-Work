<?php
if (isset ( $_GET ['mode'] )) {
	if ($_GET ['mode'] === 'new')
		require_once './addQuote.html';
} else
	require_once './showQuotes.php';
?>