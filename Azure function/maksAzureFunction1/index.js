
var Connection = require('tedious').Connection;
var Request = require('tedious').Request
var TYPES = require('tedious').TYPES;

module.exports = function (context, myTimer) {

    var _currentData = {};

    var config = {
        userName: 'maks177', // UPDATE
     password: 'vualehvist05051998M', // UPDATE
     server: 'mserver2018.database.windows.net', // UPDATE
     options: 
        {
           database: 'Lab2DB' // UPDATE
           , encrypt: true
        }
    };

    var connection = new Connection(config);
    connection.on('connect', function(err) {
        context.log("Connected");
       sendData();
    });
    async function sendData() {
        var min = 1000;
        var max = 5000;
        var b =  Math.floor(Math.random()*(max-min+1)+min);
       
       for (i = 0; i<100; i++) {
           var a=Math.floor(Math.random() * 100); 
           console.log("temperature sensor" +" " +a)
           executeStatement1(a)
         await sleep(b)
       }
   
   }
   
   function sleep(millis) {
     
       return new Promise(resolve => setTimeout(resolve, millis));
   }
   function executeStatement1(temperatureSensor) {  
       request = new Request("INSERT INTO SensorTemperature (Temperature) VALUES (@temperatureSensor)", function(err) { 
       if (err) {  
           console.log(err);}  
       });  
       request.addParameter('temperatureSensor', TYPES.NVarChar , temperatureSensor);  
       request.on('requestCompleted', function () {
          request
       });
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
    
    context.done();
};