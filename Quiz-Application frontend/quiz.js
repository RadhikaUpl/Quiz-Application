document.addEventListener("DOMContentLoaded", async () => {
    let questions = [];
    let currentIndex = 0; // Track current question index
    const maxQuestions = 10;
    let correctAnsCount = 0; // Track correct answers
    let selectedAnswers = new Array(maxQuestions).fill(null); // ✅ Store selected answers
    let queCounter=document.querySelector(".que-counter");
    let counter=0;
    let number=document.querySelector(".number");
    const optionEle = document.querySelectorAll(".opt");
    const nextBtn = document.querySelector(".nextbutton button");
    const prevButton = document.querySelector(".prebutton");
    const quiztype=document.querySelector(".quiz-type");
    const totalQUEdIV=document.querySelector(".total-questions-div");


    try {
        const Type = localStorage.getItem("selectedType");
        console.log("Selected Type:", Type);

        const response = await fetch(`http://localhost:8080/questions/${Type}`);

        if (!response.ok) {
            throw new Error("Failed to fetch questions");
        }

        questions = await response.json();
        console.log("Fetched Questions:", questions);

        // Shuffle and limit to 10 questions
        questions = questions.sort(() => Math.random() - 0.5).slice(0, maxQuestions);

        // Display first question
        displayQuestion();
    } catch (error) {
        console.log("Error:", error);
    }

    function displayQuestion() {
        if (questions.length === 0) return;

        const questionBox = document.querySelector(".que");
        const opt1 = document.querySelector(".opt1");
        const opt2 = document.querySelector(".opt2");
        const opt3 = document.querySelector(".opt3");
        const opt4 = document.querySelector(".opt4");

        quiztype.innerHTML="Quiz Type- "+localStorage.getItem("selectedType");
        totalQUEdIV.innerHTML="Total Questions "+maxQuestions;
        const currentQuestion = questions[currentIndex];

        questionBox.innerHTML = currentQuestion.questions;
        // counter++;
        // queCounter.innerHTML="Question "+counter;
        opt1.innerHTML = currentQuestion.options[0].options;
        opt2.innerHTML = currentQuestion.options[1].options;
        opt3.innerHTML = currentQuestion.options[2].options;
        opt4.innerHTML = currentQuestion.options[3].options;

        // ✅ Assign correct data & Reset selection
        optionEle.forEach((option, i) => {
            option.dataset.correct = currentQuestion.options[i].isCorrect;
            option.classList.remove("selected", "disabled", "optClick");
            option.classList.add("opt");

            // ✅ Restore previously selected option
            if (selectedAnswers[currentIndex] === i) {
                option.classList.add("selected", "optClick");
                option.classList.remove("opt");
            }
        });

        // ✅ Disable Previous button on first question
        prevButton.disabled = currentIndex === 0;
    }

    // ✅ Store selected answer when user clicks an option
    optionEle.forEach((option, i) => {
        option.addEventListener("click", () => {
            // Remove "selected" from all options
            optionEle.forEach(opt => {
                opt.classList.remove("selected", "optClick");
                opt.classList.add("opt");
            });

            // ✅ Mark the clicked option as selected
            option.classList.add("selected", "optClick");
            option.classList.remove("opt");

            // ✅ Save the selected answer in the array
            selectedAnswers[currentIndex] = i;

            console.log("✅ Stored Answers:", selectedAnswers);
        });
    });

    // ✅ Next Button Functionality
    nextBtn.addEventListener("click", () =>
         {
        
        const selectedopt = document.querySelector(".optClick"); // ✅ Get selected option
          
        if (!selectedopt) {
            alert("⚠️ Please select an option before proceeding.");
            
            return;
        } else {
            prevButton.disabled = false; // ✅ Enable Previous button
            

        }

        // ✅ Count correct answers
        if (selectedopt.dataset.correct === "true") {
            correctAnsCount++;
            console.log("Correct Answers:", correctAnsCount);
        }

        if(currentIndex < questions.length - 1) {
            currentIndex++;
           
            console.log(currentIndex);
            number.innerHTML=currentIndex+1;
            displayQuestion();
        } else {
            endQuiz(); // ✅ Show results at end
        }
    });

    // ✅ Previous Button Functionality
    prevButton.addEventListener("click", () => {
        correctAnsCount--;
        if (currentIndex > 0) {
            currentIndex--;
            number.innerHTML=currentIndex+1;
            console.log(currentIndex);
            displayQuestion();
        }

        // ✅ Disable Previous button on first question
        prevButton.disabled = currentIndex === 0;
    });

    function endQuiz() {
        document.querySelector(".quizQue").innerHTML = `
            <div style="text-align: center; padding: 50px;">
                <h2>🎉 Quiz Completed! 🎉</h2>
                <br>
                <p>Thank you for participating.</p>
                <p>Your Score: <strong>${correctAnsCount} / ${questions.length}</strong></p>
            </div>
        `;
        storeScore();
    }

    async function storeScore()
    {
        let username=localStorage.getItem("loggedInUser");
        let quiztype=localStorage.getItem("selectedType");
        let score=correctAnsCount;
         
        let obj={
            username:username,
            quiztype:quiztype,
            score:score
        };
    
        try{
            let response=await fetch(`http://localhost:8080/score/addData`,{
                method:"POST",
                headers:{
                    "Content-Type":"application/json"
                },
                body:JSON.stringify(obj)
    
            });
        }
        catch(error)
        {
            alert(error);
        }
    }
//    let queno=0;
//    nextBtn.addEventListener("click",()=>
// {
//     if (selectedopt && queno<=maxQuestions)
//     {
//         queno++;
//         console.log(queno);
//     }
// })
// prevButton.addEventListener("click",()=>
// {
//     if(queno>0)
//     {
//         queno--;
//         console.log(queno);
//     }
// })
});
