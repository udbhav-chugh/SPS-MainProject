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
// google.charts.setOnLoadCallback(drawChart);

/** Creates a chart and adds it to the page. */
function voteGraph() {

  var productid=localStorage.getItem("productid")
  fetch('/vote-response?productid='+productid  ).then(response => response.json())
  .then((obj) => {
      console.log(obj);
    var data = new google.visualization.DataTable();
   
    data.addColumn('string', 'Upvote-Downvote');
    data.addColumn('number', 'Votes');
    data.addRow(["Upvotes",obj["upvotes"]]);
    data.addRow(["Downvotes",obj["downvotes"]]);

    const options = {
      'title': 'Vote Statistics',
      'width':600,
      'height':500
    };

    const chart = new google.visualization.ColumnChart(document.getElementById('voteGraph-container'));
    chart.draw(data, options);
  });
}

function sentimentGraph() {

  var productid=localStorage.getItem("productid")
  fetch('/sentiment-score?productid='+productid  ).then(response => response.json())
  .then((obj) => {
    
    var countArr = [0,0,0,0,0];
    for(var i=0;i<obj.length;i++){
        var value = obj[i]["sentimentAnalysisScore"];
        if(value==10)
            countArr[4]+=1;
        else
            countArr[Math.floor(value/2)]+=1;
        console.log(Math.floor(value/2));
        console.log(countArr[Math.floor(value/2)]);
    }  

    var data = new google.visualization.DataTable();
   
    data.addColumn('string', 'Percentage-Positiveness');
    data.addColumn('number', 'Number of Reviewers');
    data.addRow(["0-20%",countArr[0]]);
    data.addRow(["20-40%",countArr[1]]);
    data.addRow(["40-60%",countArr[2]]);
    data.addRow(["60-80%",countArr[3]]);
    data.addRow(["80-100%",countArr[4]]);

    const options = {
      'title': 'Sentiment Analysis Statistics',
      'width':800,
      'height':500
    };

    const chart = new google.visualization.ColumnChart(document.getElementById('sentimentGraph-container'));
    chart.draw(data, options);
  });
}

function agegroupGraph() {

//   var productid=localStorage.getItem("productid")
//   fetch('/age-count?productid='+productid  ).then(response => response.json())
//   .then((obj) => {
//       console.log(obj);
//     var data = new google.visualization.DataTable();
   
//     data.addColumn('string', 'Upvote-Downvote');
//     data.addColumn('number', 'Votes');
//     data.addRow(["Upvotes",obj["upvotes"]]);
//     data.addRow(["Downvotes",obj["downvotes"]]);

//     const options = {
//       'title': 'Vote Statistics',
//       'width':600,
//       'height':500
//     };

//     const chart = new google.visualization.ColumnChart(document.getElementById('agegroupGraph-container'));
//     chart.draw(data, options);
//   });
}

