var usernameTextbox = document.getElementById("username");

usernameTextbox.addEventListener('blur', () => {
    var user = {
        'username': usernameTextbox.value
    }
    console.log(user)
    checkIfUserExists(user)
})

async function checkIfUserExists(user) {
    const responseFromServer = await fetch('http://localhost:8080/exists', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    let userExists = await responseFromServer.json()
    showUsernameError(userExists)
    if (userExists === true) {
        // user exists
        console.log("Oops, this username already exists")
        usernameTextbox.focus()
        usernameTextbox.select()
        
        showErrorAnimation().then((message) => {
            console.log("We're now in the callback function")
            console.log(message)
            usernameTextbox.style.background = 'rgb(255, 255, 255)'
        })
    }
}

function showErrorAnimation() {
    return new Promise((resolve, reject) => {
        console.log("We're in the showErrorAnimation function")
        // perform error animation
        var i = 255
        var animationInterval = setInterval ( () => {
            i--
            usernameTextbox.style.background= `rgb(255, ${i}, ${i})`
            if (i === 0) {
                clearInterval(animationInterval)
                resolve("Done executing animation code")
           }
        }, 0.5)
    })
}

function showUsernameError (userExists) {
    const errorSpan = document.getElementById("error1")
    if (userExists) {
    errorSpan.textContent="Username already exists"
    errorSpan.style.color="red"
    } else {
        errorSpan.textContent=""
    }
}