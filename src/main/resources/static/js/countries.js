let countries = document.querySelector('.countries');
let countriesAll = document.querySelector('.single-country-container');
// const token = document.querySelector('meta[name="_csrf"]').content;
// const header = document.querySelector('meta[name="_csrf_header"]').content;
let countriesList = []

let country = {
  'name': '',
  'capital': '',
  'currencies' : [],
  'languages' : [],
  'flag':''
}

saveCountries();

function saveCountries() {
  fetch('https://restcountries.com/v3.1/all')
  .then(response => response.json())
  // .then(data => testSaveCountries(data))
  .then(data => data.forEach(country => {
    country = {
      'name' : country.name.common,
      'capital' : country.capital,
      'currencies' : country.currencies,
      'languages': country.languages,
      'flag':country.flag
    }
    console.log(country)
    countriesList.push(country)
    testSaveCountries(countriesList)

  }))

}
  function testSaveCountries(countriesList){
    countriesList.forEach(country=> {
      fetch('/save-all-countries', {
        method: 'POST',
        headers: {
          'Content-Type' : 'application/JSON'
        },
        body:JSON.stringify(country)
      })
      .then(response => response.json())
      .then(data => console.log(data))
    })
  }
// function testSaveCountries(data){
//   data.forEach(country => {
//     fetch('/save-all-countries', {
//       method: 'POST',
//       headers: {
//         'Content-Type' : 'application/JSON',
//         header : token
//       },
//       body:JSON.stringify(country)
//     })
//     .then(response => response.json())
//     .then(data => console.log(data))
    
//   });
//   }























// let countriesDiv = document.getElementById('countryId')
// const apiUrl = `https://restcountries.com/v3.1/all`;

// document.addEventListener('DOMContentLoaded', () => {

//   function fetchCountries() {
//     fetch(apiUrl)
//       .then(response => response.json())
//       .then(data => show(data))

//       //  For Testing Purposes :
//       // (data => data.forEach(item => console.log(item)))
//       // (data => show(data));

// }

//   function show(data) {
//     const countryNameArray = [];

//     data.forEach(country => {

      
//       countryNameArray.push(country.name.common);

//         const countryUL = document.querySelector('#country-list');
  
//         const countryLi = document.createElement('li');
//         const p = document.createElement('p');
//         const link = document.createElement('a');
  
//         p.innerHTML = country.name.common;
//         countryLi.append(p);
//         countryUL.append(countryLi);
      
      
//     })
//     // console.log(countryNameArray)
//     countryNameArray.sort();
//     console.log(countryNameArray);

//   }
//   fetchCountries();

//   async function getCountryByName(name) {

//   }
// })