console.log("In signup");

async function submitForm(){
	const fName = document.getElementById("fName").value;
	const lName = document.getElementById("lName").value;
	const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;
	const cpassword = document.getElementById("cpassword").value;
	
	console.log(fName,lName,email,password);
	const body = {"fName":fName,"lName":lName,"email":email,"password":password,"cpassword":cpassword};
	const resp=await fetch("/signup",{
		method:"POST",
		body:JSON.stringify(body),
		headers:{
			'Content-type':'application/json'
		}
	});
	const data = await resp.json();
	console.log(data);
	


}





