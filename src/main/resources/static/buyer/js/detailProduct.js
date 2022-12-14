
const productQuantity = document.querySelector(".amount-product");
const plusBtn = document.querySelector(".btn-plus");
const minusBtn = document.querySelector(".btn-minus");
const addBtn = document.querySelector(".add-btn");
const modalSuccess = document.querySelector(".modal-success");

productQuantity.addEventListener('input', editValue);

$(document).ready(function() {
	productQuantity.addEventListener('input', () => {
		if (+productQuantity.value > +productQuantity.max) {
			productQuantity.value = +productQuantity.max;
		}
		
	});
	
})

function editValue(){
	if (+productQuantity.value == 1) {
		minusBtn.setAttribute("disabled", "true");
	} else {
		minusBtn.removeAttribute("disabled");
	}
	
	if (+productQuantity.value == +productQuantity.max) {
		plusBtn.setAttribute("disabled", "true");
	} else {
		plusBtn.removeAttribute("disabled");
	}
	
}

plusBtn.addEventListener('click', (e) => {
	e.preventDefault();
	minusBtn.removeAttribute("disabled");
	if (+productQuantity.value == +productQuantity.ariaValueMax) {
		plusBtn.setAttribute("disabled", "true");
	} else {
		plusBtn.removeAttribute("disabled");
	}
})


minusBtn.addEventListener('click', (e) => {
	e.preventDefault();
	plusBtn.removeAttribute("disabled");
	if (+productQuantity.value == 1) {
		minusBtn.setAttribute("disabled", "true");
	} else {
		minusBtn.removeAttribute("disabled");
	}
})

if (modalSuccess) {
	setTimeout(() => {
		modalSuccess.classList.remove("show");
		modalSuccess.style.display="none";
	}, 2000)
	
}



