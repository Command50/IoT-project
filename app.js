var express = require("express");
var bodyParser = require("body-parser");
 
var app = express();
 
var urlencodedParser = bodyParser.urlencoded({extended: false});

var Connection = require('tedious').Connection;
var Request = require('tedious').Request;
var TYPES = require('tedious').TYPES;  

var config = 
   {
     userName: 'maks177', // UPDATE
     password: 'vualehvist05051998M', // UPDATE
     server: 'mserver2018.database.windows.net', // UPDATE
     options: 
        {
           database: 'Data_from_sensors' // UPDATE
           , encrypt: true
        }
   }
var connection = new Connection(config);

	connection.on('connect', function(err) 
		{
		 if (err) 
		   {
			  console.log(err)
		   }
		else
		   {
			   //
		   }
		}
	);

app.use(express.static(__dirname + "/public"));
 
app.post("/testwebpage", urlencodedParser, function (request, response) {
    if(!request.body) return response.sendStatus(400);
    console.log(request.body);
    //response.send(`${request.body.userName} - ${request.body.userLastName}`);
	executeStatement1(request.body.temperatureSensor, request.body.nameSensor);
});
 
app.get("/", function(request, response){
		queryDatabase(response);
});
 
function queryDatabase(response)
{ console.log('Reading rows from the Table...');
  let result = "";
	
       // Read all rows from table
     request = new Request(
          "SELECT * FROM Sensor",
             function(err, rowCount, rows) 
                {
                    console.log(rowCount + ' row(s) returned');
					response.send(result);
                }
            );

     request.on('row', function(columns) {
        columns.forEach(function(column) {
			// Column name and column value
			//result += `${column.metadata.colName}  ${column.value} `;
			
			//Column value only
			result += `${column.value} `;
			
			//For debug
            //console.log("%s\t%s", column.metadata.colName, column.value);
         });
		 result += `<br>`;
    });
    connection.execSql(request);
}

function executeStatement1(temperatureSensor, nameSensor) {  
        request = new Request("INSERT INTO SENSOR (Temperature, SensorName) VALUES (@temperatureSensor, @nameSensor)", function(err) { 
		if (err) {  
            console.log(err);}  
        });  
        request.addParameter('temperatureSensor', TYPES.NVarChar , temperatureSensor);  
        request.addParameter('nameSensor', TYPES.NVarChar, nameSensor);
        request.on('row', function(columns) {  
            columns.forEach(function(column) {  
              if (column.value === null) {  
                console.log('NULL');  
              } else {  
                console.log("Person id of inserted item is " + column.value);  
              }  
            });  
        });       
        connection.execSql(request);  
    }  
 
app.listen(3000);