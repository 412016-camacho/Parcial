using CriptoBack.Data.Entities;
using CriptoBack.Data.Entities.Repositories;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CriptoBack.Data.Repositories
{
    public class CriptoRepository : ICriptoRepository
    {
        private readonly CriptomonedasDbContext _context;
        public CriptoRepository(CriptomonedasDbContext context)
        {
            _context = context;
        }
        public async Task<bool> Delete(int id)
        {
            var criptoEliminar = await _context.Criptomonedas.FindAsync(id);
            if(criptoEliminar != null)
            {
                criptoEliminar.Estado = "NH";
                _context.Criptomonedas.Update(criptoEliminar);
            }
            return await _context.SaveChangesAsync()>0;
        }

        public async Task<List<Criptomoneda>> GetAllByCategory(Categoria categoria)
        {
            return await _context.Criptomonedas.Where(x=>x.CategoriaNavigation.Id==categoria.Id).Where(xe=>xe.UltimaActualizacion >= DateTime.Today.AddDays(-1)) .ToListAsync();
        }

        public async Task<bool> UpdateValue(int id, double valor, string simbolo, DateTime fecha)
        {
            var criptoActual = await _context.Criptomonedas.FindAsync(id);
            var criptoActualizar = await _context.Criptomonedas.Where(x => x.Simbolo == simbolo).ToListAsync();
            if (valor != null && simbolo != null && criptoActual!= null && fecha!= null)
            {
                criptoActual.ValorActual = valor;
                criptoActual.UltimaActualizacion = fecha;
                if (criptoActualizar != null)
                    _context.Criptomonedas.Update(criptoActual);
            }
            return await _context.SaveChangesAsync() > 0;
        }
    }
}
