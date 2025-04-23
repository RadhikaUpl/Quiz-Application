let modalDiv=document.querySelector(".modal");

document.addEventListener("DOMContentLoaded",()=>
{
    const quizbutton=document.querySelectorAll(".take-quiz");
    quizbutton.forEach(button=>{
        button.addEventListener("click",()=>
        {
            if(profileNav.innerHTML==localStorage.getItem("loggedInUser"))
            {
            const section=event.target.getAttribute("data-section");//getting the type of the quiz selected using data-section
           
            if(section)
            {
              
                localStorage.setItem("selectedType",section);//storing type of the quiz to localstorage

                window.location.href="quiz.html";
            }
             }
             else
                {
                    modalDiv.classList.remove("displayModel");
                    return;
                }

});
   
    });

});


const login=document.querySelector("#login");
console.log(login);


login.addEventListener("click",()=>
{
        modalDiv.classList.remove("displayModel");  
});
let close=document.querySelector(".close");

close.addEventListener("click",()=>
{
    modalDiv.classList.add("displayModel");  
});

const signinDiv=document.querySelector(".signinClass");
console.log(signinDiv);
const signIninLogin=document.querySelector("#signIn");
console.log(signIninLogin);

signIninLogin.addEventListener("click",()=>
{
    signinDiv.classList.remove("displayModel");
    modalDiv.classList.add("displayModel");
})
const signInclose=document.querySelector(".closeSignin");
signInclose.addEventListener("click",()=>
{
    signinDiv.classList.add("displayModel");
})
const loginBtnInSignin=document.querySelector("#signInBelow");
loginBtnInSignin.addEventListener("click",()=>
{
    signinDiv.classList.add("displayModel");
    modalDiv.classList.remove("displayModel");
})


const signInBtn =document.querySelector("#signUpForm");


signInBtn.addEventListener("submit",async(event)=>
{
   
    event.preventDefault();
    // alert("clicked");
    const email=document.getElementById("emailId").value;
    const username=document.getElementById("Newusername").value;
    const password=document.getElementById("Newpassword").value;
    const photo=document.getElementById("photo").files[0];

    console.log(photo);
    console.log(email);
    console.log(username);
    console.log(password);

    if(email==='' || username==='' || password==='' || !photo)
    {
        alert("Enter complete data");
        return;
    }
   

    let  formData=new FormData();
    formData.append("email",email);
    formData.append("username",username);
    formData.append("password",password);
    formData.append("photo",photo);

    // console.log(formData);
    try{
        let response =await fetch(`http://localhost:8080/signIn/newuser`,
            {
            method:"POST",
            body:formData

        })
        response= await response.json();
        if(response)
            {
                alert("successfully created Account");
                signinDiv.classList.add("displayModel");
                
            }
            else
            {
                alert("Account Already exits on this email");
            }
    }
    catch(error)
    {
        alert("username already exists");
    }
});
const loginbtn=document.querySelector("#loginInForm");
loginbtn.addEventListener("submit",async(event)=>
{
    event.preventDefault();

    let username=document.getElementById("username").value;
    let password= document.getElementById("password").value;

    if(username==='' || password==='')
    {
        alert("Please enter complete details");
    }
    const formData={
        username:username,
        password:password
    };
    

    try{
        let response=await fetch("http://localhost:8080/signIn/loginUser",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(formData)

        });
        response= await response.json();
        if(response)
            {
                alert("login successfully");
                localStorage.setItem("loggedInUser", username); 
                uodateLoginUI(username);
               modalDiv.classList.add("displayModel");
              
             
            }
            else
            {
                alert("Account not exists");
            }
    }
    catch(error)
    {
        alert(error);
    }
});

let loginUserNav=document.querySelector(".loginUserNav");
let profileNav=document.querySelector("#ProfileNav");
let profileDisplay=document.querySelector(".profileDisplay");

function uodateLoginUI(username)
{
    let login=document.getElementById("login");
    if(login)
    {
        loginUserNav.classList.add("displayModel");
        profileNav.classList.remove("displayModel");
        
       profileNav.innerHTML=username;
         login.classList.remove("disabled-link");
    }
}

document.addEventListener("DOMContentLoaded",()=>
{
    let loginuser=localStorage.getItem("loggedInUser");
    if(loginuser)
    {
        uodateLoginUI(loginuser);
    }

});

let logout=document.querySelector(".logoutBtn");
logout.addEventListener("click",()=>
{

    let username=localStorage.getItem("loggedInUser");
    if(username)
    {
        alert("want to logout");
        profileNav.classList.add("displayModel");
        profileDisplay.classList.add("displayProfile");
        login.classList.remove("displayModel");
        localStorage.removeItem("loggedInUser");
        login.innerText="LOGIN";
        login.classList.remove("disabled-link");
    }

})
document.addEventListener("DOMContentLoaded",async()=>
    {
           try{
               
                let response=await fetch(`http://localhost:8080/score/getTopper`);
                response= await response.json();
                console.log(response);
                response.forEach(Score=>{
                
                let table=document.querySelector("table");
                let row=document.createElement("tr");
                row.innerHTML=
                `<td>${Score.username}</td>
                <td>${Score.quiztype}</td>
                <td>${Score.score}</td>`;
    
                table.append(row);
    
                })
            }
            catch(error)
            {
                alert(error);
            }
    
         
    });
    profileNav.addEventListener("click",async()=>
    {
        if(profileDisplay.classList.contains("displayProfile"))
        {
            let username=localStorage.getItem("loggedInUser");
            let profilepic=document.querySelector(".profile-pic");
            let name=document.querySelector("#name");
            let email=document.querySelector("#email");
            profileDisplay.classList.remove("displayProfile");
            try{
                let response= await fetch(`http://localhost:8080/signIn/getDetails/${username}`);

                if(!response.ok)
                {
                    throw new error;
                }
                response=await response.json();
                console.log(response);
                
                profilepic.src=response.imgUrl;

                name.innerHTML="Name : "+response.username;
                email.innerHTML="Email : "+response.email;

            }
            catch(error)
            {
                console.log(error);
            }
        }
       

    })
    let crossProfile=document.querySelector(".closeProfile");
    crossProfile.addEventListener("click",()=>
    {
        profileDisplay.classList.add("displayProfile");
    })
    

    let notes=document.querySelectorAll(".subject");

    notes.forEach(note=>{
        note.addEventListener("click",async ()=>{
            try{
                let response=await fetch(`http://localhost:8080/api/download/${note.innerText}`);

                if(!response.ok)
                {
                    throw new error;
                }

                response=await response.blob();
                let a=document.createElement("a");
                a.href=URL.createObjectURL(response);
                a.download=note.innerText+"notes.pdf";
                a.click();

            }
            catch(error)
            {
                alert(error);
            }
        })
    })