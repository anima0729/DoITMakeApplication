<?php 

    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('navercon.php');
        

    $stmt = $con->prepare('select * from naver_list_id');
    $stmt->execute();

    if ($stmt->rowCount() > 0)
    {
        $data = array(); 

        while($row=$stmt->fetch(PDO::FETCH_ASSOC))
        {
            extract($row);
    
            array_push($data, 
                array('id'=>$id,
	  'list_id'=>$list_id,
            ));
        }

        header('Content-Type: application/json; charset=utf8');
        $json = json_encode(array("naver_list_id"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
        echo $json;
    }

?>