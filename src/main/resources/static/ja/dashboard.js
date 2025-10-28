/* dashboard.js
  Frontend expects REST endpoints on your backend:
  GET  /api/students           -> list students
  POST /api/students           -> create student (JSON body)
  PUT  /api/students/{id}      -> update student
  DELETE /api/students/{id}    -> delete student

  If your Spring app uses a different base path, update API_BASE in main.js
*/

(async function init() {
    // auth check
    if (!getToken()) {
        // redirect to login
        window.location.href = 'index.html';
        return;
    }

    // elements
    const openAdd = document.getElementById('openAdd');
    const modalBackdrop = document.getElementById('modalBackdrop');
    const modalCard = document.querySelector('.modal-card');
    const modalClose = document.getElementById('modalClose');
    const modalSave = document.getElementById('modalSave');
    const modalClear = document.getElementById('modalClear');
    const modalMsg = document.getElementById('modalMsg');

    // inputs
    const mName = document.getElementById('mName');
    const mRoll = document.getElementById('mRoll');
    const mClass = document.getElementById('mClass');
    const mEmail = document.getElementById('mEmail');
    const mCourse = document.getElementById('mCourse');
    const mGrade = document.getElementById('mGrade');
    const mNotes = document.getElementById('mNotes');

    // students table
    const studentsTableBody = document.querySelector('#studentsTable tbody');
    const studentCount = document.getElementById('studentCount');
    const studentsSection = document.getElementById('studentsSection');

    // open modal
    openAdd && openAdd.addEventListener('click', openModal);

    function openModal() {
        modalBackdrop.style.display = 'flex';
        setTimeout(() => modalCard.classList.add('show'), 20);
    }

    function closeModal() {
        modalCard.classList.remove('show');
        setTimeout(() => modalBackdrop.style.display = 'none', 300);
    }
    modalClose.addEventListener('click', closeModal);
    modalBackdrop.addEventListener('click', e => { if (e.target === modalBackdrop) closeModal(); });

    modalClear.addEventListener('click', () => {
        mName.value = '';
        mRoll.value = '';
        mClass.value = '';
        mEmail.value = '';
        mCourse.value = '';
        mGrade.value = '';
        mNotes.value = '';
    });

    // save student
    modalSave.addEventListener('click', async() => {
        const payload = {
            name: mName.value.trim(),
            rollNumber: mRoll.value.trim(),
            email: mEmail.value.trim(),
            course: mCourse.value.trim(),
            grade: mGrade.value.trim(),
            notes: mNotes.value.trim()
        };
        if (!payload.name || !payload.rollNumber) {
            modalMsg.textContent = 'Name and roll required';
            return;
        }
        modalSave.disabled = true;
        modalSave.textContent = 'Saving...';
        try {
            // POST to backend
            const created = await apiFetch('/api/students', { method: 'POST', body: JSON.stringify(payload) });
            modalMsg.textContent = 'Saved';
            loadStudents();
            setTimeout(() => {
                closeModal();
                modalSave.disabled = false;
                modalSave.textContent = 'Save';
            }, 600);
        } catch (err) {
            console.error(err);
            // fallback to localStorage demo if backend not available
            const arr = JSON.parse(localStorage.getItem('adduha_students') || '[]');
            arr.push({ id: Date.now(), ...payload });
            localStorage.setItem('adduha_students', JSON.stringify(arr));
            loadStudents();
            modalMsg.textContent = 'Saved (local demo)';
            modalSave.disabled = false;
            modalSave.textContent = 'Save';
            setTimeout(() => closeModal(), 600);
        }
    });

    // load students from backend (or localStorage fallback)
    async function loadStudents() {
        try {
            const list = await apiFetch('/api/students');
            renderStudents(list);
        } catch (err) {
            console.warn('Backend error, using local demo', err);
            const list = JSON.parse(localStorage.getItem('adduha_students') || '[]');
            renderStudents(list);
        }
    }

    function renderStudents(list) {
        studentsTableBody.innerHTML = '';
        if (!list || !list.length) {
            studentsTableBody.innerHTML = '<tr><td colspan="7" class="muted">No students</td></tr>';
            studentCount.textContent = '0';
            return;
        }
        studentCount.textContent = list.length;
        for (const s of list) {
            const tr = document.createElement('tr');
            tr.innerHTML = `
        <td>${s.id||''}</td>
        <td>${escapeHtml(s.name||'')}</td>
        <td>${escapeHtml(s.rollNumber||'')}</td>
        <td>${escapeHtml(s.email||'')}</td>
        <td>${escapeHtml(s.course||'')}</td>
        <td>${escapeHtml(s.grade||'')}</td>
        <td>
          <button class="btn-ghost" data-id="${s.id}" data-action="edit">Edit</button>
          <button class="btn-ghost" data-id="${s.id}" data-action="del">Delete</button>
        </td>
      `;
            studentsTableBody.appendChild(tr);
        }
    }

    // table actions (delegation)
    studentsTableBody.addEventListener('click', async(e) => {
        const b = e.target.closest('button');
        if (!b) return;
        const id = b.getAttribute('data-id');
        const action = b.getAttribute('data-action');
        if (action === 'del') {
            if (!confirm('Delete this student?')) return;
            try {
                await apiFetch(`/api/students/${id}`, { method: 'DELETE' });
                loadStudents();
            } catch (err) {
                console.warn('Delete failed, trying local demo', err);
                let arr = JSON.parse(localStorage.getItem('adduha_students') || '[]');
                arr = arr.filter(x => String(x.id) !== String(id));
                localStorage.setItem('adduha_students', JSON.stringify(arr));
                loadStudents();
            }
        } else if (action === 'edit') {
            // fill modal with student data and open it (for brevity we use local fetch)
            try {
                const s = await apiFetch(`/api/students/${id}`);
                mName.value = s.name || '';
                mRoll.value = s.rollNumber || '';
                mClass.value = s.course || '';
                mEmail.value = s.email || '';
                mCourse.value = s.course || '';
                mGrade.value = s.grade || '';
                mNotes.value = s.notes || '';
                openModal();
                // change save to update behavior
                modalSave.onclick = async() => {
                    const payload = { name: mName.value, rollNumber: mRoll.value, email: mEmail.value, course: mCourse.value, grade: mGrade.value, notes: mNotes.value };
                    await apiFetch(`/api/students/${id}`, { method: 'PUT', body: JSON.stringify(payload) });
                    loadStudents();
                    closeModal();
                    // restore default
                    modalSave.onclick = null;
                };
            } catch (err) {
                alert('Unable to load student for edit.');
            }
        }
    });

    // nav behaviour (students button shows list)
    document.querySelectorAll('.nav-btn').forEach(btn => {
        btn.addEventListener('click', (e) => {
            document.querySelectorAll('.nav-btn').forEach(x => x.classList.remove('active'));
            btn.classList.add('active');
            const route = btn.dataset.route;
            if (route === 'students') {
                studentsSection.classList.remove('hidden');
                document.querySelector('.grid').classList.add('hidden');
                loadStudents();
            } else {
                studentsSection.classList.add('hidden');
                document.querySelector('.grid').classList.remove('hidden');
            }
        });
    });

    // logout
    document.getElementById('logoutBtn').addEventListener('click', () => {
        clearToken();
        window.location.href = 'index.html';
    });

    // initial load
    loadStudents();
})();