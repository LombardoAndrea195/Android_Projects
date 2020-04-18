<?php

if (!empty($_POST)) { // Attendo la ricezione di una richiesta POST
		

	$arr = array('email' => $_POST['email'], 'password' => $_POST['password'], 'idrace' => $_POST['idrace'], 'description' => $_POST['Description'], 'location' => $_POST['Location'],
			   'distance' => $_POST['Distance'], 'date' => $_POST['Date'], 'before' => $_POST['Before'], 'url' => $_POST['url'], 'note' => $_POST['Note'], 'picture' => $_POST['picture']);
	$arr['picture'] = stripslashes($arr['picture']);
	$arr['date'] = stripslashes($arr['date']);
	$arr['before'] = stripslashes($arr['before']);
	$arr['url'] = stripslashes($arr['url']);

				
    $str = file_get_contents('Iscrizioni.json'); // Carico il contenuto del file 'Iscrizioni.json'
    $jsonDecod = json_decode($str, JSON_UNESCAPED_SLASHES); // Trasformo il file .json in un array
    array_push($jsonDecod, $arr); // Aggiungo il mio array a quelle precedentemente presenti
    $str = json_encode($jsonDecod, JSON_UNESCAPED_SLASHES); // Ricodifico il nuovo Array in json
    $file = fopen('Iscrizioni.json','w'); // Apro il file per la scrittura 
    fwrite($file, $str); // Sovrascrivo il contenuto del file
    fclose($file); // Chiudo il file
				
				



	}
	else
	{

		print("Non ho ricevuto alcun POST!");




}

?>