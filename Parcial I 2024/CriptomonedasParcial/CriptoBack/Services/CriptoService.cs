using CriptoBack.Data.Entities;
using CriptoBack.Data.Entities.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CriptoBack.Services
{
    public class CriptoService : ICriptoService
    {
        private readonly ICriptoRepository _repository;
        public CriptoService(ICriptoRepository repository)
        {
            _repository = repository;
        }
        public async Task<bool> ActualizarValores(int id, double valor, string simbolo, DateTime fecha)
        {
            if(ValidarFecha(fecha))
                return await _repository.UpdateValue(id,valor,simbolo, fecha);
            return false;
        }

        public Task<bool> CancelarCripto(int id)
        {
            return _repository.Delete(id);
        }

        public Task<List<Criptomoneda>> TraerCategorias(Categoria categoria)
        {
            return _repository.GetAllByCategory(categoria);
        }

        public bool ValidarFecha(DateTime fecha)
        {
            return fecha.CompareTo(DateTime.Today) ==-1 || fecha.CompareTo(DateTime.Today) >= 0;
        }
    }
}