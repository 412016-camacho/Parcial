using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CriptoBack.Data.Entities.Repositories
{
    public interface ICriptoRepository
    {
        Task<List<Criptomoneda>> GetAllByCategory(Categoria categoria);
        Task<bool> UpdateValue(int id, double valor, string simbolo, DateTime fecha);
        Task<bool> Delete(int id);
    }
}
