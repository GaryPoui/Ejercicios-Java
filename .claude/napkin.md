# Napkin Runbook

## Curation Rules
- Re-prioritize on every read.
- Keep recurring, high-value notes only.
- Max 10 items per category.
- Each item includes date + "Do instead".

## Execution & Validation
1. **[2026-09-22] Java sources are in `stock_precios_java (2)`**
   Do instead: compile the sources from that directory with `javac` before handing off changes.
2. **[2026-09-22] The project has no automated test suite**
   Do instead: compile every source with `javac -Xlint:all`, run `Main`, and use a temporary same-package harness for edge cases.

## Domain Behavior Guardrails
1. **[2026-09-22] Combo stock is limited by the least-stocked item**
   Do instead: return zero for an empty combo and the minimum `calcularStock()` across its items.
