// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

/** Creates a chart and adds it to the page. */
function drawChart() {

  var productid=localStorage.getItem("productid")
  fetch('/vote-response?productid='+productid  ).then(response => response.json())
  .then((obj) => {
      console.log(obj);
    var data = new google.visualization.DataTable();
    console.log("work");

    console.log(obj.ProjectID);
    console.log(obj.upvotes);

    data.addColumn('string', 'ProjectID');
    data.addColumn('number', 'Votes');
    Object.keys(obj).forEach((color) => {
       data.addRow([color, obj[color]]);
    });

    const options = {
      'title': 'Favorite Colors',
      'width':600,
      'height':500
    };

    const chart = new google.visualization.ColumnChart(
        document.getElementById('chart-container'));
    chart.draw(data, options);
  });
}

(function () {
    if (!console) {
        console = {};
    }
    var old = console.log;
    var logger = document.getElementById('log');
    console.log = function (message) {
        if (typeof message == 'object') {
            logger.innerHTML += (JSON && JSON.stringify ? JSON.stringify(message) : String(message)) + '<br />';
        } else {
            logger.innerHTML += message + '<br />';
        }
    }
})();

console.log("fuck you");
/** Fetches color data and uses it to create a chart. */
/** function drawChart11() {
  fetch('/vote-response').then(response => response.json())
  .then((obj) => {
    const data = new google.visualization.DataTable(obj);
    //data.addColumn('string', 'Color');
    //data.addColumn('number', 'Votes');
    //Object.keys(obj).forEach((color) => {
      //data.addRow([color, colorVotes[color]]);
    });

    const options = {
      'title': 'Favorite Colors',
      'width':600,
      'height':500
    };

    const chart = new google.visualization.ColumnChart(
        document.getElementById('chart-container'));
    chart.draw(data, options);
  }


function drawChart() {
  const data = new google.visualization.DataTable();
  data.addColumn('string', 'Animal');
  data.addColumn('number', 'Count');
        data.addRows([
          ['Lions', 10],
          ['Tigers', 5],
          ['Bears', 15]
        ]);

  const options = {
    'title': 'Zoo Animals',
    'width':500,
    'height':400
  };

  const chart = new google.visualization.PieChart(
      document.getElementById('chart-container'));
  chart.draw(data, options);
}
*/