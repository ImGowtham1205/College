// Auto-hide server messages
setTimeout(() => {
  document.querySelectorAll('.msg-box').forEach(msg => {
    msg.classList.add('fade-out');
  });
}, 3000); // fades out after 3s 3000);

const form = document.getElementById('studentForm');

// Validation on submit
form.addEventListener('submit', function(e) {
  let valid = true;
  const inputs = this.querySelectorAll('input, select, textarea');

  inputs.forEach(input => {
    const errorText = input.parentElement.querySelector('.error-text');
    if (errorText) errorText.textContent = '';
    input.classList.remove('error-border');

    if (!input.checkValidity()) {
      valid = false;
      input.classList.add('error-border');
      if (errorText) errorText.textContent = 'This field is required';
    }
  });

  // Gender radio validation
  const genderInputs = document.querySelectorAll('input[name="gender"]');
  const genderError = genderInputs[0].closest('.input-group').querySelector('.error-text');
  if (![...genderInputs].some(g => g.checked)) {
    valid = false;
    genderError.textContent = 'Please select a gender';
  } else {
    genderError.textContent = '';
  }

  if (!valid) e.preventDefault();
});

// 🔹 Remove error message as soon as user starts typing/selecting
form.querySelectorAll('input, select, textarea').forEach(input => {
  input.addEventListener('input', function() {
    const errorText = this.parentElement.querySelector('.error-text');
    if (this.checkValidity()) {
      this.classList.remove('error-border');
      if (errorText) errorText.textContent = '';
      this.classList.add('success-border'); // optional ✅
    } else {
      this.classList.remove('success-border');
    }
  });
});

// For select fields (change event is better)
form.querySelectorAll('select').forEach(select => {
  select.addEventListener('change', function() {
    const errorText = this.parentElement.querySelector('.error-text');
    if (this.checkValidity()) {
      this.classList.remove('error-border');
      if (errorText) errorText.textContent = '';
      this.classList.add('success-border');
    } else {
      this.classList.remove('success-border');
    }
  });
});

// For radio buttons (gender)
document.querySelectorAll('input[name="gender"]').forEach(radio => {
  radio.addEventListener('change', () => {
    const genderError = radio.closest('.input-group').querySelector('.error-text');
    genderError.textContent = '';
    document.querySelectorAll('input[name="gender"]').forEach(r => r.classList.remove('error-border'));
  });
});