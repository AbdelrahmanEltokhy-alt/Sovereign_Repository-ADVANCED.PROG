/**
 * main.js — Sovereign
 * Scroll nav, scroll reveal, color swatch, mod price total
 */
document.addEventListener('DOMContentLoaded', () => {

  // ── Nav scroll effect ─────────────────────────────────────────────────
  const nav = document.querySelector('nav');
  if (nav && !nav.classList.contains('nav-solid')) {
    window.addEventListener('scroll', () => {
      nav.classList.toggle('scrolled', window.scrollY > 60);
    }, { passive: true });
  }

  // ── Scroll reveal ─────────────────────────────────────────────────────
  const reveals = document.querySelectorAll('.reveal');
  if (reveals.length) {
    const obs = new IntersectionObserver((entries) => {
      entries.forEach(e => {
        if (e.isIntersecting) { e.target.classList.add('visible'); obs.unobserve(e.target); }
      });
    }, { threshold: 0.08, rootMargin: '0px 0px -40px 0px' });
    reveals.forEach(el => obs.observe(el));
  }

  // ── Color swatch selection ────────────────────────────────────────────
  const swatches = document.querySelectorAll('.swatch-circle, .swatch-btn');
  swatches.forEach(s => {
    s.addEventListener('click', function () {
      const group = this.closest('.color-swatches, .color-swatches-grid');
      if (group) group.querySelectorAll('.swatch-circle, .swatch-btn').forEach(x => x.classList.remove('selected'));
      this.classList.add('selected');
    });
  });

  // ── Mod selection + running total ─────────────────────────────────────
  const modItems = document.querySelectorAll('.mod-item[data-price], .mod-card[data-price]');
  const totalEl  = document.getElementById('build-total');
  const baseEl   = document.getElementById('base-price');

  if (modItems.length && totalEl) {
    const base = parseFloat(baseEl?.dataset.base || '0');
    const fmt  = n => '$' + n.toLocaleString('en-US');

    modItems.forEach(item => {
      item.style.cursor = 'pointer';
      item.addEventListener('click', () => {
        item.classList.toggle('selected');
        const check = item.querySelector('.mod-check');
        if (check) check.textContent = item.classList.contains('selected') ? '✓' : '';
        let extras = 0;
        modItems.forEach(m => { if (m.classList.contains('selected')) extras += parseFloat(m.dataset.price || 0); });
        totalEl.textContent = fmt(base + extras);
      });
    });
  }
});
